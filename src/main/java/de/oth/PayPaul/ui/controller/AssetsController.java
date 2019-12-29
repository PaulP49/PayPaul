package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.service.implementation.AssetsService;
import de.oth.PayPaul.service.interfaces.IAssetsService;
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

  @RequestMapping(value = "/paymentMethods/addNewBankAccount", method = RequestMethod.POST)
  public String addNewBankAccount(@ModelAttribute("bankAccount") BankAccount bankAccount, RedirectAttributes redirectAttributes) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      assetsService.createNewBankAccountForUser(auth.getName(), bankAccount);
      redirectAttributes.addFlashAttribute("successMessage",
              new CustomResponse("Neues Bankkonto hinzugefügt!", "Sie können jetzt über diese Zahlungsmethode Ihr Guthaben aufladen."));
      return "redirect:/paymentMethods";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage",
              new CustomResponse("Bankkonto konnte nicht hinzugefügt werden.", "Fehler: " + e.getMessage()));
      return "redirect:/paymentMethods/addNew";
    }
  }

  @RequestMapping(value = "/paymentMethods/addNewCreditCard", method = RequestMethod.POST)
  public String addNewCreditCard(@ModelAttribute("creditCard") CreditCard creditCard, RedirectAttributes redirectAttributes) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      assetsService.createNewCreditCardForUser(auth.getName(), creditCard);
      redirectAttributes.addFlashAttribute("successMessage",
              new CustomResponse("Neue Kreditkarte hinzugefügt!", "Sie können jetzt über diese Zahlungsmethode Ihr Guthaben aufladen."));
      return "redirect:/paymentMethods";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage",
              new CustomResponse("Kreditkarte konnte nicht hinzugefügt werden.", "Fehler: " + e.getMessage()));
      return "redirect:/paymentMethods/addNew";
    }
  }
}
