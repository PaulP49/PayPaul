package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.persistence.model.PaymentMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {
  @Query("SELECT M FROM Account A LEFT JOIN A.paymentMethods P INNER JOIN CreditCard M ON P.id = M.id WHERE A.email=:email and M.cardNumber IS NOT null")
  public List<CreditCard> findAllCreditCardsForUser(@Param("email") String email);

  @Query("SELECT M FROM Account A LEFT JOIN A.paymentMethods P INNER JOIN BankAccount M ON P.id = M.id WHERE A.email=:email and M.IBAN IS NOT null")
  public List<BankAccount> findAllBankAccountsForUser(@Param("email") String email);

  @Query("SELECT CASE WHEN COUNT(M) > 0 THEN TRUE ELSE FALSE END FROM Account A LEFT JOIN A.paymentMethods P INNER JOIN BankAccount M ON P.id = M.id where M.IBAN=:iban and A.email=:email")
  public boolean bankAccountExists(@Param("email") String email, @Param("iban") String iban);

  @Query("SELECT CASE WHEN COUNT(M) > 0 THEN TRUE ELSE FALSE END FROM Account A LEFT JOIN A.paymentMethods P INNER JOIN CreditCard M ON P.id = M.id where M.cardNumber=:cardNumber and A.email=:email")
  public boolean creditCardExists(@Param("email") String email, @Param("cardNumber") Long cardNumber);
}
