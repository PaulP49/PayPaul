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

  @OneToMany
  private List<PaymentMethod> paymentMethods;

  @OneToMany
  private List<PaymentNotification> paymentNotifications;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getCredit() {
    return credit;
  }

  public void addCredit(int amount) {
    credit += amount;
  }

  public void removeCredit(int amount) throws Exception {
    if (credit >= amount) {
      credit -= amount;
    } else {
      throw new Exception("Nicht genug Guthaben vorhanden. Bitte aufladen.");
    }
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public List<PaymentMethod> getPaymentMethods() {
    return paymentMethods;
  }

  public void addPaymentMethod(PaymentMethod paymentMethod) {
    paymentMethods.add(paymentMethod);
  }

  public List<PaymentNotification> getPaymentNotifications() {
    return paymentNotifications;
  }

  public void addPaymentNotification(PaymentNotification paymentNotification) {
    paymentNotifications.add(paymentNotification);
  }

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
