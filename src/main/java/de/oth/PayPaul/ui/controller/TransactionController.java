package de.oth.PayPaul.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {

  @RequestMapping(value = "/transactions", method = RequestMethod.GET)
  public String getTransactionsView(Model model) {
    return "transactions";
  }

  @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
  public String getSendMoneyView(Model model) {
    return "sendMoney";
  }
}
