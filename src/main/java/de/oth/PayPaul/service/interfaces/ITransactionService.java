package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.service.model.TransactionDTO;
import de.oth.PayPaul.service.model.TransactionRequestException;

import java.util.List;
import java.util.Map;

public interface ITransactionService {
  Map<String, List<Transaction>> getAllTransactionsForUser(String email);
  Account getCurrentUser(String email);
  void createNewTransactionFromUser(String email, Transaction transaction) throws Exception;
  Transaction requestTransaction(TransactionDTO transaction) throws TransactionRequestException;
}
