package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.repository.AccountRepository;
import de.oth.PayPaul.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
  private AccountRepository accountRepo;

  @Autowired
  public void setRepo(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
  }
}
