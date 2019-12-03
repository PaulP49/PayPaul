package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.repository.TransactionRepository;
import de.oth.PayPaul.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {
  private TransactionRepository transactionRepo;

  @Autowired
  public void setRepo(TransactionRepository transactionRepo) {
    this.transactionRepo = transactionRepo;
  }
}
