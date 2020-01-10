package de.oth.PayPaul.service.model;

public class CompletedTransactionDTO {
  private int id;
  private String sender;
  private String receiver;
  private String paymentReference;
  private int amount;

  public CompletedTransactionDTO(int id, String sender, String receiver, int amount, String paymentReference) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
    this.paymentReference = paymentReference;
    this.amount = amount;
  }

  public int getId() {
    return id;
  }

  public String getSender() {
    return sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getPaymentReference() {
    return paymentReference;
  }

  public int getAmount() {
    return amount;
  }
}
