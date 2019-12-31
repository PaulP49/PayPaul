package de.oth.PayPaul.service.model;

public class TransactionRequestException extends Exception {
  String errorMessage;

  public TransactionRequestException(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
