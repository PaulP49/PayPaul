package de.oth.PayPaul.persistence.repository;

import de.oth.PayPaul.persistence.model.PaymentMethod;
import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Integer> {}
