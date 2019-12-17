package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.repository.PaymentMethodRepository;
import de.oth.PayPaul.service.interfaces.IAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetsService implements IAssetsService {
  private PaymentMethodRepository paymentMethodRepo;

  @Autowired
  public void setRepo(PaymentMethodRepository paymentMethodRepo) {
    this.paymentMethodRepo = paymentMethodRepo;
  }


}
