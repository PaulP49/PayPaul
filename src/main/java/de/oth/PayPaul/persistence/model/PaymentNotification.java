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
