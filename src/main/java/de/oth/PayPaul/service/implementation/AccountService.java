package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.repository.AccountRepository;
import de.oth.PayPaul.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
  private AccountRepository accountRepo;
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public void setPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.passwordEncoder = bCryptPasswordEncoder;
  }

  @Autowired
  public void setRepo(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
  }

  public void createNewAccount(Account account) throws Exception {
    account.setPasswordHash(passwordEncoder.encode(account.getPasswordHash()));
    if (!accountRepo.existsById(account.getEmail())) {
      accountRepo.save(account);
    } else {
      throw new Exception("Es existiert bereits ein Account mit der angegebenen Email.");
    }
  }
}
