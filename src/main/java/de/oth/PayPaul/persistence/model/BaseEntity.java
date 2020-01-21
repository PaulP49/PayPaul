package de.oth.PayPaul.persistence.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity<K extends Serializable> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public K id;

  public K getId() {
    return id;
  }

  public void setId(K id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    return o != null && getClass() == o.getClass() && Objects.equals(getId(), ((BaseEntity<?>) o).getId());
  }

  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }
}
