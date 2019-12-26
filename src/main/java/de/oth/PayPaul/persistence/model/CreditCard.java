package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;

@Entity
@AccessType(AccessType.Type.FIELD)
public class CreditCard extends PaymentMethod {

  private Long cardNumber;

  private String cardType;

  private Integer checkNumber;

  public Long getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(Long cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public Integer getCheckNumber() {
    return checkNumber;
  }

  public void setCheckNumber(Integer checkNumber) {
    this.checkNumber = checkNumber;
  }
}
