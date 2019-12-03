package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.PaymentNotification;
import org.springframework.data.repository.CrudRepository;

public interface PaymentNotificationRepository extends CrudRepository<PaymentNotification, Integer> {}
