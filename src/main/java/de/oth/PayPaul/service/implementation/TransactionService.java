package de.oth.PayPaul.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.PaymentNotification;
import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.persistence.repository.AccountRepository;
import de.oth.PayPaul.persistence.repository.PaymentNotificationRepository;
import de.oth.PayPaul.persistence.repository.TransactionRepository;
import de.oth.PayPaul.service.interfaces.ITransactionService;
import de.oth.PayPaul.service.model.CompletedTransactionDTO;
import de.oth.PayPaul.service.model.TransactionDTO;
import de.oth.PayPaul.service.model.TransactionRequestException;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements ITransactionService {
  private TransactionRepository transactionRepo;
  private AccountRepository accountRepo;
  private PaymentNotificationRepository notificationRepo;

  @Autowired
  public void setRepo(TransactionRepository transactionRepo) {
    this.transactionRepo = transactionRepo;
  }

  @Autowired
  public void setAccountRepo(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
  }

  @Autowired
  public void setNotificationRepo(PaymentNotificationRepository notificationRepo) {
    this.notificationRepo = notificationRepo;
  }

  @Override
  public Map<String, List<Transaction>> getAllTransactionsForUser(String email) {
    Map<String, List<Transaction>> map = new HashMap<>();
    map.put("incoming", transactionRepo.findByReceiver_Email(email));
    map.put("outgoing", transactionRepo.findBySender_Email(email));
    return map;
  }

  @Override
  public Account getCurrentUser(String email) {
    return accountRepo.findByEmail(email);
  }

  @Override
  @Transactional
  public void createNewTransactionFromUser(String email, Transaction transaction) throws Exception {
    if (!email.equals(transaction.getSender().getEmail())) {
      throw new Exception("Authentifizierungsfehler beim Erstellen der Transaktion.");
    }
    Account receiver = accountRepo.findById(transaction.getReceiver().getEmail()).orElseThrow(
            () -> new Exception("Empfänger konnte nicht gefunden werden."));
    transaction.getSender().removeCredit(transaction.getAmount());
    receiver.addCredit(transaction.getAmount());
    transaction.setReceiver(receiver);
    transactionRepo.save(transaction);
    sendNotificationsOnNewTransaction(transaction);
  }

  @Override
  @Transactional
  public Transaction requestTransaction(TransactionDTO transactionDTO) throws TransactionRequestException {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Account sender = accountRepo.findById(auth.getName()).orElseThrow(
              () -> new TransactionRequestException("Authentifizierungsfehler beim Erstellen der Transaktion")
      );
      Account receiver = accountRepo.findById(transactionDTO.getReceiver()).orElseThrow(
              () -> new TransactionRequestException("Empfänger konnte nicht gefunden werden")
      );
      Transaction transaction = new Transaction();
      transaction.setSender(sender);
      transaction.setReceiver(receiver);
      transaction.setAmount(transactionDTO.getAmount());
      transaction.setPaymentReference(transactionDTO.getPaymentReference());

      sender.removeCredit(transactionDTO.getAmount());
      receiver.addCredit(transactionDTO.getAmount());

      transactionRepo.save(transaction);
      sendNotificationsOnNewTransaction(transaction);
      return transaction;
    } catch (Exception e) {
      throw new TransactionRequestException(e.getMessage());
    }
  }

  private void sendNotificationsOnNewTransaction(Transaction transaction) throws URISyntaxException, IOException, InterruptedException {
    List<PaymentNotification> notificationsSender = notificationRepo.findAllForSender(transaction.getSender().getEmail());
    List<PaymentNotification> notificationsReceiver = notificationRepo.findAllForReceiver(transaction.getReceiver().getEmail());

    if (notificationsSender.isEmpty() && notificationsReceiver.isEmpty())
      return;

    HttpClient client = HttpClient.newHttpClient();
    ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
    CompletedTransactionDTO transactionDTO = new CompletedTransactionDTO(transaction.getId(),
            transaction.getSender().getEmail(), transaction.getReceiver().getEmail(),
            transaction.getAmount(), transaction.getPaymentReference());
    String jsonTransaction = writer.writeValueAsString(transactionDTO);

    for(PaymentNotification notification : notificationsSender) {
      postNotification(client, notification.getTargetUrl().toURI(), jsonTransaction);
    }
    for(PaymentNotification notification : notificationsReceiver) {
      postNotification(client, notification.getTargetUrl().toURI(), jsonTransaction);
    }
  }

  private HttpResponse<String> postNotification(HttpClient client, URI target, String body) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
            .uri(target)
            .POST(HttpRequest.BodyPublishers.ofString(body)).build();
    return client.send(request, HttpResponse.BodyHandlers.ofString());
  }
}
