package de.oth.PayPaul.persistence.model;


public enum TransactionStatus {
  InProgress,
  Completed,
  Failed;

  @Override
  public String toString() {
    switch(this) {
      case InProgress: return "In Progress";
      case Completed: return "Completed";
      case Failed: return "Failed";
      default: throw new IllegalArgumentException();
    }
  }
}
