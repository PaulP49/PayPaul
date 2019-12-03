package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {}
