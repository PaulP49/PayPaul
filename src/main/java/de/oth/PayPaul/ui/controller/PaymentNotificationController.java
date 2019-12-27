package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.PaymentNotification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentNotificationController {

  @RequestMapping(value = "/paymentNotifications", method = RequestMethod.GET)
  public String getPaymentNotificationsView(Model model) {
    return "paymentNotifications";
  }

  @RequestMapping(value = "/paymentNotifications/addNew", method = RequestMethod.GET)
  public String getNewPaymentNotificationView(Model model) {
    model.addAttribute("paymentNotification", new PaymentNotification());
    return "newPaymentNotification";
  }

  @RequestMapping(value = "/paymentNotifications/addNew", method = RequestMethod.POST)
  public String newPaymentNotification(Model model, @ModelAttribute("paymentNotification")PaymentNotification paymentNotification) {
    return "";
  }
}
