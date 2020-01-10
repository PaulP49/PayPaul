package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.PaymentNotification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentNotificationRepository extends CrudRepository<PaymentNotification, Integer> {
  @Query("SELECT paymentNotifications FROM Account WHERE email=:email")
  public List<PaymentNotification> findAllForUser(@Param("email") String email);

  @Query("SELECT P FROM Account A LEFT JOIN A.paymentNotifications P WHERE A.email=:email AND P.id=:id")
  public PaymentNotification findByIdFromUser(@Param("email") String email, @Param("id") int id);

  @Query("SELECT P FROM Account A LEFT JOIN A.paymentNotifications P WHERE A.email=:email AND P.active=TRUE AND P.forOutgoingPayments=TRUE")
  public List<PaymentNotification> findAllForSender(@Param("email") String email);

  @Query("SELECT P FROM Account A LEFT JOIN A.paymentNotifications P WHERE A.email=:email AND P.active=TRUE AND P.forIncomingPayments=TRUE")
  public List<PaymentNotification> findAllForReceiver(@Param("email") String email);
}
