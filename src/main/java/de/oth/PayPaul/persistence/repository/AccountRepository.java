package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {}
