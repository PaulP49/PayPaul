package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;

@Entity
@AccessType(AccessType.Type.FIELD)
public class CreditCard extends PaymentMethod {

  private long cardNumber;

  private String cardType;

  private int checkNumber;
}
