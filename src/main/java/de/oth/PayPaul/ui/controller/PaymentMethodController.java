package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentMethodController {

  @RequestMapping(value = "/paymentMethods", method = RequestMethod.GET)
  public String getPaymentMethodsView(Model model) {
    return "paymentMethods";
  }

  @RequestMapping(value = "/paymentMethods/addNew", method = RequestMethod.GET)
  public String getNewPaymentMethodView(Model model) { ;
    model.addAttribute("bankAccount", new BankAccount());
    model.addAttribute("creditCard", new CreditCard());
    return "newPaymentMethod";
  }

  @RequestMapping(value = "/paymentMethods/addNew", method = RequestMethod.POST)
  public String addNewPaymentMethod(Model model, @ModelAttribute("bankAccount") BankAccount bankAccount,
                                    @ModelAttribute("creditCard") CreditCard creditCard) {
    return "";
  }

}
