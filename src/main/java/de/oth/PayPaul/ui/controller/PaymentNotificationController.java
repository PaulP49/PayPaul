package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.PaymentNotification;
import de.oth.PayPaul.service.implementation.NotificationService;
import de.oth.PayPaul.service.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentNotificationController {

  private INotificationService notificationService;

  @Autowired
  public void setInterface(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @RequestMapping(value = "/paymentNotifications", method = RequestMethod.GET)
  public String getPaymentNotificationsView(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("notifications", notificationService.getAllNotificationsForUser(auth.getName()));
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
