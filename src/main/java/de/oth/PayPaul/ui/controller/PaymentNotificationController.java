package de.oth.PayPaul.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentNotificationController {

  @RequestMapping(value = "/paymentNotifications", method = RequestMethod.GET)
  public String paymentNotifications(Model model) {
    return "paymentNotifications";
  }

  @RequestMapping(value = "/paymentNotifications/addNew", method = RequestMethod.GET)
  public String newPaymentNotification(Model model) {
    return "newPaymentNotification";
  }
}
