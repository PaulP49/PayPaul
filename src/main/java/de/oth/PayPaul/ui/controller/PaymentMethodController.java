package de.oth.PayPaul.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentMethodController {

  @RequestMapping(value = "/paymentMethods", method = RequestMethod.GET)
  public String paymentMethods(Model model) {
    return "paymentMethods";
  }

  @RequestMapping(value = "/paymentMethods/addNew", method = RequestMethod.GET)
  public String newPaymentMethod(Model model) {
    return "newPaymentMethod";
  }
}
