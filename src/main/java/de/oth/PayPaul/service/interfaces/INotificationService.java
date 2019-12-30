package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.PaymentNotification;

import java.util.List;

public interface INotificationService {
  public List<PaymentNotification> getAllNotificationsForUser(String email);
  public void createNewNotificationForUser(String email, PaymentNotification paymentNotification);
  public void activateNotificationWithId(String email, int id);
  public void deactivateNotificationWithId(String email, int id);

}
