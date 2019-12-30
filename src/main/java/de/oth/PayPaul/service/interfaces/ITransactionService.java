package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.Transaction;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
  public Map<String, List<Transaction>> getAllTransactionsForUser(String email);
  public Account getCurrentUser(String email);
  public void createNewTransactionFromUser(String email, Transaction transaction) throws Exception;
}
