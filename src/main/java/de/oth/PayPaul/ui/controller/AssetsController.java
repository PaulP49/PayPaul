package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.service.implementation.AssetsService;
import de.oth.PayPaul.service.interfaces.IAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AssetsController {
  private IAssetsService assetsService;

  @Autowired
  public void setInterface(AssetsService assetsService) {
    this.assetsService = assetsService;
  }

  @RequestMapping(value = "/paymentMethods", method = RequestMethod.GET)
  public String getPaymentMethodsView(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("creditCards", assetsService.getAllCreditCardsForUser(auth.getName()));
    model.addAttribute("bankAccounts", assetsService.getAllBankAccountsForUser(auth.getName()));
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
