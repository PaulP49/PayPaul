package de.oth.PayPaul.persistence.model;

import javax.persistence.*;
import org.springframework.data.annotation.AccessType;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AccessType(AccessType.Type.FIELD)
public class PaymentMethod {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Temporal(TemporalType.DATE)
  private Date expiryDate;

  @Min(value = 0, message = "Pay Limit must be greater than 0")
  private int payLimit = Integer.MAX_VALUE;

  private boolean active = true;

  private boolean blocked = false;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    return Objects.equals(id, ((PaymentMethod) o).id);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }
}
