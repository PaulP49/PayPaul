package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.persistence.repository.TransactionRepository;
import de.oth.PayPaul.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements ITransactionService {
  private TransactionRepository transactionRepo;

  @Autowired
  public void setRepo(TransactionRepository transactionRepo) {
    this.transactionRepo = transactionRepo;
  }

  @Override
  public Map<String, List<Transaction>> getAllTransactionsForUser(String email) {
    Map<String, List<Transaction>> map = new HashMap<>();
    map.put("incoming", transactionRepo.findByReceiver_Email(email));
    map.put("outgoing", transactionRepo.findBySender_Email(email));
    return map;
  }
}
