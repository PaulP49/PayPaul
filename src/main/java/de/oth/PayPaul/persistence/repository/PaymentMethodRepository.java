package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.persistence.model.PaymentMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {
  @Query("SELECT M FROM Account A LEFT JOIN A.paymentMethods P INNER JOIN CreditCard M ON P.id = M.id WHERE A.email=:email")
  public List<CreditCard> findAllCreditCardsForUser(@Param("email") String email);

  @Query("SELECT M FROM Account A LEFT JOIN A.paymentMethods P INNER JOIN BankAccount M ON P.id = M.id WHERE A.email=:email")
  public List<BankAccount> findAllBankAccountsForUser(@Param("email") String email);
}
