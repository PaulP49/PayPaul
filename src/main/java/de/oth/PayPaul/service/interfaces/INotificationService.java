package de.oth.PayPaul.service.interfaces;

import de.oth.PayPaul.persistence.model.PaymentNotification;

import java.util.List;

public interface INotificationService {
  public List<PaymentNotification> getAllNotificationsForUser(String email);
}
