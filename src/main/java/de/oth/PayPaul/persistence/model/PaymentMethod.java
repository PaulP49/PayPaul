package de.oth.PayPaul.persistence.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.AccessType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AccessType(AccessType.Type.FIELD)
public class PaymentMethod extends BaseEntity<Integer> {
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String expiryDate = null;

  @Min(value = 0, message = "Pay Limit must be greater than 0")
  @Max(value = Integer.MAX_VALUE, message = "Choose a smaller number")
  private Integer payLimit;

  private boolean active = true;

  private boolean blocked = false;

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Integer getPayLimit() {
    return payLimit;
  }

  public void setPayLimit(Integer payLimit) {
    this.payLimit = payLimit;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  @Override
  public String toString() {
    return getClass().getName();
  }
}
