package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.BankAccount;
import de.oth.PayPaul.persistence.model.CreditCard;
import de.oth.PayPaul.service.implementation.AssetsService;
import de.oth.PayPaul.service.interfaces.IAccountService;
import de.oth.PayPaul.service.interfaces.IAssetsService;
import de.oth.PayPaul.ui.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AssetsController {
  private IAssetsService assetsService;
  private IAccountService accountService;

  @Autowired
  public void setInterface(AssetsService assetsService) {
    this.assetsService = assetsService;
  }

  @Autowired
  public void setAccountInterface(IAccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(value = "/paymentMethods", method = RequestMethod.GET)
  public String getPaymentMethodsView(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    model.addAttribute("creditCards", assetsService.getAllCreditCardsForUser(email));
    model.addAttribute("bankAccounts", assetsService.getAllBankAccountsForUser(email));
    model.addAttribute("credit", accountService.getCreditByEmail(email));
    return "paymentMethods";
  }

  @RequestMapping(value = "/paymentMethods/addNew", method = RequestMethod.GET)
  public String getNewPaymentMethodView(Model model) {
    model.addAttribute("bankAccount", new BankAccount());
    model.addAttribute("creditCard", new CreditCard());

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("credit", accountService.getCreditByEmail(auth.getName()));
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

  @RequestMapping(value = "/paymentMethods/activateMethod", method = RequestMethod.POST)
  public ResponseEntity<String> activateMethodWithId(@RequestParam int id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    assetsService.activateMethodWithId(auth.getName(), id);
    return new ResponseEntity<>("activated " + id, HttpStatus.OK);
  }

  @RequestMapping(value = "/paymentMethods/deactivateMethod", method = RequestMethod.POST)
  public ResponseEntity<String> deactivateMethodWithId(@RequestParam int id) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    assetsService.deactivateMethodWithId(auth.getName(), id);
    return new ResponseEntity<>("activated " + id, HttpStatus.OK);
  }

  @RequestMapping(value = "/chargeCredit", method = RequestMethod.GET)
  public String chargeCredit(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    List<BankAccount> bankAccounts = assetsService.getAllBankAccountsForUser(email);
    List<CreditCard> creditCards = assetsService.getAllCreditCardsForUser(email);
    ArrayList<String> paymentMethodSelection = new ArrayList<String>();
    bankAccounts.forEach(b -> {
      if (b.isActive()) {
        String iban = b.getIBAN();
        if (iban.length() > 3)
          iban = "***" + iban.substring(iban.length() - 3);
        paymentMethodSelection.add("Bankkonto IBAN: " + iban);
      }
    });
    creditCards.forEach(c -> {
      if (c.isActive()) {
        String number = Long.toString(c.getCardNumber());
        if (number.length() > 3)
          number = "***" + number.substring(number.length() - 3);
        paymentMethodSelection.add(c.getCardType() + " Nr: " + number);
      }
    });
    model.addAttribute("paymentMethods", paymentMethodSelection);
    model.addAttribute("amount", 0);
    model.addAttribute("credit", accountService.getCreditByEmail(email));
    return "chargeCredit";
  }

  @RequestMapping(value = "/chargeCredit", method = RequestMethod.POST)
  public String chargeCredit(@RequestParam("amount") int amount,
                             RedirectAttributes redirectAttributes) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      assetsService.chargeCredit(auth.getName(), amount);
      redirectAttributes.addFlashAttribute("successMessage",
              new CustomResponse(amount + "€ wurden Ihrem Guthaben gutgeschrieben.", "Das Guthaben kann absofort für Transaktionen verwendet werden."));
      return "redirect:/transactions";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage",
              new CustomResponse("Fehler! Guthaben konnte nicht aufgeladen werden.", "Fehler: " + e.getMessage()));
      return "redirect:/chargeCredit";
    }
  }
}
