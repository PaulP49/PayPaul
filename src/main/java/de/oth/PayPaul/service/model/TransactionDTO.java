package de.oth.PayPaul.service.model;

public class TransactionDTO {
  private String paymentReference;
  private int amount;
  private String receiver;

  public TransactionDTO(String receiver, int amount, String paymentReference) {
    this.paymentReference = paymentReference;
    this.amount = amount;
    this.receiver = receiver;
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

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
}
