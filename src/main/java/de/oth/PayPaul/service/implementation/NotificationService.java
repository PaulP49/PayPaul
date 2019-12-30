package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.PaymentNotification;
import de.oth.PayPaul.persistence.repository.AccountRepository;
import de.oth.PayPaul.persistence.repository.PaymentNotificationRepository;
import de.oth.PayPaul.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationService implements INotificationService {
  private PaymentNotificationRepository notificationRepo;
  private AccountRepository accountRepo;

  @Autowired
  public void setRepo(PaymentNotificationRepository notificationRepo) {
    this.notificationRepo = notificationRepo;
  }

  @Autowired
  public void setAccountRepo(AccountRepository accountRepo) {
    this.accountRepo = accountRepo;
  }

  @Override
  public List<PaymentNotification> getAllNotificationsForUser(String email) {
    return notificationRepo.findAllForUser(email);
  }

  @Override
  @Transactional
  public void createNewNotificationForUser(String email, PaymentNotification paymentNotification) {
    notificationRepo.save(paymentNotification);
    Account currUser = accountRepo.findByEmail(email);
    currUser.addPaymentNotification(paymentNotification);
  }

  @Override
  @Transactional
  public void activateNotificationWithId(String email, int id) {
    PaymentNotification notification = notificationRepo.findByIdFromUser(email, id);
    notification.setActive(true);
  }

  @Override
  @Transactional
  public void deactivateNotificationWithId(String email, int id) {
    PaymentNotification notification = notificationRepo.findByIdFromUser(email, id);
    notification.setActive(false);
  }
}
