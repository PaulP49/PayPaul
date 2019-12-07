package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;

@Entity
@AccessType(AccessType.Type.FIELD)
public class CreditCard extends PaymentMethod {

  private long cardNumber;

  private String cardType;

  private int checkNumber;

  public long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public int getCheckNumber() {
    return checkNumber;
  }

  public void setCheckNumber(int checkNumber) {
    this.checkNumber = checkNumber;
  }
}
