package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import java.util.List;

public interface IAssetsService {
  List<CreditCard> getAllCreditCardsForUser(String email);
  List<BankAccount> getAllBankAccountsForUser(String email);
  void createNewBankAccountForUser(String email, BankAccount bankAccount) throws Exception;
  void createNewCreditCardForUser(String email, CreditCard creditCard) throws Exception;
  void activateMethodWithId(String email, int id);
  void deactivateMethodWithId(String email, int id);
  void chargeCredit(String email, int amount);
}
