package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URL;
import java.util.Objects;

@Entity
@AccessType(AccessType.Type.FIELD)
public class PaymentNotification {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private URL targetUrl;

  private boolean active = true;

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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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
