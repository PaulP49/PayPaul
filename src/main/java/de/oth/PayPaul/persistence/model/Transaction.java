package de.oth.PayPaul.persistence.model;

import org.springframework.data.annotation.AccessType;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@AccessType(AccessType.Type.FIELD)
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String paymentReference;

  @Min(value = 0, message = "Amount must be greater than null")
  private int amount;

  @ManyToOne
  private Account sender;

  @ManyToOne
  private Account receiver;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TransactionStatus transactionStatus = TransactionStatus.InProgress;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPaymentReference() {
    return paymentReference;
  }

  public void setPaymentReference(String paymentReference) {
    this.paymentReference = paymentReference;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Account getSender() {
    return sender;
  }

  public void setSender(Account sender) {
    this.sender = sender;
  }

  public Account getReceiver() {
    return receiver;
  }

  public void setReceiver(Account receiver) {
    this.receiver = receiver;
  }

  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    return Objects.equals(id, ((Transaction) o).id);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(id);
  }
}
