package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.persistence.model.PaymentMethod;
import de.oth.PayPaul.persistence.repository.PaymentMethodRepository;
import de.oth.PayPaul.service.interfaces.IAssetsService;
import net.bytebuddy.utility.JavaConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetsService implements IAssetsService {
  private PaymentMethodRepository paymentMethodRepo;

  @Autowired
  public void setRepo(PaymentMethodRepository paymentMethodRepo) {
    this.paymentMethodRepo = paymentMethodRepo;
  }


  @Override
  public List<CreditCard> getAllCreditCardsForUser(String email) {
    return paymentMethodRepo.findAllCreditCardsForUser(email);
  }

  @Override
  public List<BankAccount> getAllBankAccountsForUser(String email) {
    return paymentMethodRepo.findAllBankAccountsForUser(email);
  }
}
