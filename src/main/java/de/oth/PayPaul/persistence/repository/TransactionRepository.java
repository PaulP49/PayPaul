package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
  List<Transaction> findByReceiver_Email(String email);
  List<Transaction> findBySender_Email(String email);
}
