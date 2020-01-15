package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.PaymentNotification;
import java.util.List;

public interface INotificationService {
  List<PaymentNotification> getAllNotificationsForUser(String email);
  void createNewNotificationForUser(String email, PaymentNotification paymentNotification);
  void activateNotificationWithId(String email, int id);
  void deactivateNotificationWithId(String email, int id);

}
