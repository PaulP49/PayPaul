package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.persistence.repository.AccountRepository;
import de.oth.PayPaul.persistence.repository.TransactionRepository;
import de.oth.PayPaul.service.interfaces.ITransactionService;
import de.oth.PayPaul.service.model.TransactionDTO;
import de.oth.PayPaul.service.model.TransactionRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements ITransactionService {
  private TransactionRepository transactionRepo;
  private AccountRepository accountRepo;

  @Autowired
  public void setRepo(TransactionRepository transactionRepo) {
    this.transactionRepo = transactionRepo;
  }

  @Autowired
  public void setAccountRepo(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
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
  }

  @Override
  @Transactional
  public Transaction requestTransaction(TransactionDTO transaction) throws TransactionRequestException {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Account sender = accountRepo.findById(auth.getName()).orElseThrow(
              () -> new TransactionRequestException("Authentifizierungsfehler beim Erstellen der Transaktion")
      );
      Account receiver = accountRepo.findById(transaction.getReceiver()).orElseThrow(
              () -> new TransactionRequestException("Empfänger konnte nicht gefunden werden")
      );
      Transaction transaction1 = new Transaction();
      transaction1.setSender(sender);
      transaction1.setReceiver(sender);
      transaction1.setAmount(transaction.getAmount());
      transaction1.setPaymentReference(transaction.getPaymentReference());

      sender.removeCredit(transaction.getAmount());
      receiver.addCredit(transaction.getAmount());

      transactionRepo.save(transaction1);
      return transaction1;
    } catch (Exception e) {
      throw new TransactionRequestException(e.getMessage());
    }
  }
}
