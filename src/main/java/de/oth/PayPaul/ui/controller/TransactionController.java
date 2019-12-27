package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.service.implementation.TransactionService;
import de.oth.PayPaul.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {

  ITransactionService transactionService;

  @Autowired
  public void setInterface(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @RequestMapping(value = "/transactions", method = RequestMethod.GET)
  public String getTransactionsView(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("transactions", transactionService.getAllTransactionsForUser(auth.getName()));
    return "transactions";
  }

  @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
  public String getSendMoneyView(Model model) {
    Transaction transaction = new Transaction();
    transaction.setReceiver(new Account());
    model.addAttribute("transaction", transaction);
    return "sendMoney";
  }

  @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
  public String sendMoney(Model model, @ModelAttribute("transaction") Transaction transaction) {
    return "";
  }
}
