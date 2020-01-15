package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.Account;

public interface IAccountService {
  void createNewAccount(Account account) throws Exception;
  int getCreditByEmail(String email);
}
