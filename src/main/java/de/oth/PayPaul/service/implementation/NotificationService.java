package de.oth.PayPaul.service.implementation;

import de.oth.PayPaul.persistence.repository.PaymentNotificationRepository;
import de.oth.PayPaul.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {
  private PaymentNotificationRepository notificationRepo;

  @Autowired
  public void setRepo(PaymentNotificationRepository notificationRepo) {
    this.notificationRepo = notificationRepo;
  }
}
