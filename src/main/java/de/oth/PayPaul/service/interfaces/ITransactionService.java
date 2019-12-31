package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.service.model.TransactionDTO;
import de.oth.PayPaul.service.model.TransactionRequestException;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
  public Map<String, List<Transaction>> getAllTransactionsForUser(String email);
  public Account getCurrentUser(String email);
  public void createNewTransactionFromUser(String email, Transaction transaction) throws Exception;
  public Transaction requestTransaction(TransactionDTO transaction) throws TransactionRequestException;
}
