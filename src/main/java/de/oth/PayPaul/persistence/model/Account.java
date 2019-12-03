package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@AccessType(AccessType.Type.FIELD)
@Embeddable
public class Account {

  @Id
  @NotNull
  private String email;

  private String firstName;

  private String lastName;

  private int credit = 0;

  private String passwordHash;

  @ElementCollection
  @ManyToMany
  private List<Transaction> transactions;

  @ElementCollection
  @OneToMany
  private List<PaymentMethod> paymentMethods;

  @ElementCollection
  @OneToMany
  private List<PaymentNotification> paymentNotifications;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    return Objects.equals(email, ((Account) o).email);
  }

  @Override
  public int hashCode() {
    return email != null ? email.hashCode() : 0;
  }
}
