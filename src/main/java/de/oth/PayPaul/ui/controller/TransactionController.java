package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.persistence.model.Transaction;
import de.oth.PayPaul.service.implementation.TransactionService;
import de.oth.PayPaul.service.interfaces.IAccountService;
import de.oth.PayPaul.service.interfaces.ITransactionService;
import de.oth.PayPaul.ui.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransactionController {

  ITransactionService transactionService;
  IAccountService accountService;

  @Autowired
  public void setInterface(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Autowired
  public void setAccountInterface(IAccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(value = "/transactions", method = RequestMethod.GET)
  public String getTransactionsView(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("transactions", transactionService.getAllTransactionsForUser(auth.getName()));
    model.addAttribute("credit", accountService.getCreditByEmail(auth.getName()));
    return "transactions";
  }

  @RequestMapping(value = "/sendMoney", method = RequestMethod.GET)
  public String getSendMoneyView(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Transaction transaction = new Transaction();
    transaction.setSender(transactionService.getCurrentUser(auth.getName()));
    transaction.setReceiver(new Account());
    model.addAttribute("transaction", transaction);
    model.addAttribute("credit", accountService.getCreditByEmail(auth.getName()));

    return "sendMoney";
  }

  @RequestMapping(value = "/sendMoney", method = RequestMethod.POST)
  public String sendMoney(@ModelAttribute("transaction") Transaction transaction, RedirectAttributes redirectAttributes) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      transactionService.createNewTransactionFromUser(auth.getName(), transaction);
      redirectAttributes.addFlashAttribute("successMessage",
              new CustomResponse("Sie haben " + transaction.getAmount() + "€ an " + transaction.getReceiver().getEmail() + " gezahlt.",
                      "Details zu der Transaktion finden Sie in der Übersicht."));
      return "redirect:/transactions";
    } catch(Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage",
              new CustomResponse("Fehler beim Erstellen der Transaktion.", "Fehler: " + e.getMessage()));
      return "redirect:/sendMoney";
    }
  }
}
