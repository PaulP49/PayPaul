package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;

import javax.persistence.Entity;

@Entity
@AccessType(AccessType.Type.FIELD)
public class BankAccount extends PaymentMethod {

  private String IBAN;

  private String BIC;

  private String accountOwner;
}
