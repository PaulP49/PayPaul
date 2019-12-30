package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.persistence.model.PaymentMethod;
import de.oth.PayPaul.persistence.repository.AccountRepository;
import de.oth.PayPaul.persistence.repository.PaymentMethodRepository;
import de.oth.PayPaul.service.interfaces.IAssetsService;
import net.bytebuddy.utility.JavaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetsService implements IAssetsService {
  private PaymentMethodRepository paymentMethodRepo;
  private AccountRepository accountRepo;

  @Autowired
  public void setRepo(PaymentMethodRepository paymentMethodRepo) {
    this.paymentMethodRepo = paymentMethodRepo;
  }

  @Autowired
  public void setAccountRepo(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
  }


  @Override
  public List<CreditCard> getAllCreditCardsForUser(String email) {
    return paymentMethodRepo.findAllCreditCardsForUser(email);
  }

  @Override
  public List<BankAccount> getAllBankAccountsForUser(String email) {
    return paymentMethodRepo.findAllBankAccountsForUser(email);
  }

  @Override
  @Transactional
  public void createNewBankAccountForUser(String email, BankAccount bankAccount) throws Exception {
    if(!paymentMethodRepo.bankAccountExists(email, bankAccount.getIBAN())) {
        Account account = accountRepo.findById(email).orElseThrow(
                () -> new Exception("Authentifizierungsproblem")
        );
        paymentMethodRepo.save(bankAccount);
        account.addPaymentMethod(bankAccount);
    }
  }

  @Override
  @Transactional
  public void createNewCreditCardForUser(String email,CreditCard creditCard) throws Exception {
    if(!paymentMethodRepo.creditCardExists(email, creditCard.getCardNumber())) {
      Account account = accountRepo.findById(email).orElseThrow(
              () -> new Exception("Authentifizierungsproblem")
      );
      paymentMethodRepo.save(creditCard);
      account.addPaymentMethod(creditCard);
    }
  }

  @Override
  @Transactional
  public void activateMethodWithId(String email, int id) {
    PaymentMethod paymentMethod = paymentMethodRepo.findPaymentMethodByIdFromUser(email, id);
    paymentMethod.setActive(true);
  }

  @Override
  @Transactional
  public void deactivateMethodWithId(String email, int id) {
    PaymentMethod paymentMethod = paymentMethodRepo.findPaymentMethodByIdFromUser(email, id);
    paymentMethod.setActive(false);
  }
}
