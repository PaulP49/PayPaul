package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Entity
@AccessType(AccessType.Type.FIELD)
public class PaymentNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private URL targetUrl;

  private boolean forIncomingPayments;

  private boolean forOutgoingPayments;

  private boolean active = true;

  @ManyToMany
  private List<PaymentMethod> forPaymentMethods;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public URL getTargetUrl() {
    return targetUrl;
  }

  public void setTargetUrl(URL targetUrl) {
    this.targetUrl = targetUrl;
  }

  public boolean isForIncomingPayments() {
    return forIncomingPayments;
  }

  public void setForIncomingPayments(boolean forIncomingPayments) {
    this.forIncomingPayments = forIncomingPayments;
  }

  public boolean isForOutgoingPayments() {
    return forOutgoingPayments;
  }

  public void setForOutgoingPayments(boolean forOutgoingPayments) {
    this.forOutgoingPayments = forOutgoingPayments;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public List<PaymentMethod> getForPaymentMethods() {
    return forPaymentMethods;
  }

  public void setForPaymentMethods(List<PaymentMethod> forPaymentMethods) {
    this.forPaymentMethods = forPaymentMethods;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    return Objects.equals(id, ((PaymentNotification) o).id);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }
}
