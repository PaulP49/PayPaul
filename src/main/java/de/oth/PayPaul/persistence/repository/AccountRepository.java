package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

  Account findByEmail(String email);

  @Query("SELECT credit FROM Account WHERE email=:email")
  int findCreditByEmail(String email);
}
