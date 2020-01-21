package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;
import javax.persistence.*;
import java.net.URL;
import java.util.Objects;

@Entity
@AccessType(AccessType.Type.FIELD)
public class PaymentNotification extends BaseEntity<Integer> {
  private URL targetUrl;

  private boolean forIncomingPayments;

  private boolean forOutgoingPayments;

  private boolean active = true;

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
}
