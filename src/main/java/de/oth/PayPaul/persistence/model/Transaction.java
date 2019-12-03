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
