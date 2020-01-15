package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.service.implementation.AccountService;
import de.oth.PayPaul.service.interfaces.IAccountService;
import de.oth.PayPaul.ui.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
  IAccountService accountService;

  @Autowired
  public void setInterface(AccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String getRegisterView(Model model) {
    model.addAttribute("account", new Account());
    return "register";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(@ModelAttribute("account") Account account, @RequestParam("password_confirm") String passwordConfirm, RedirectAttributes redirectAttributes) {
    try {
      if (!account.getPasswordHash().equals(passwordConfirm))
        throw new Exception("Passwörter müssen identisch sein.");
      accountService.createNewAccount(account);
      redirectAttributes.addFlashAttribute("successMessage",
              new CustomResponse("Account wurde erfolgreich erstellt!", "Sie können sich jetzt mit ihren Logindetails anmelden."));
      return "redirect:/login";
    } catch(Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage",
              new CustomResponse("Account konnte nicht erstellt werden.", "Fehler:\n" + e.getMessage()));
      return "redirect:/register";
    }
  }
}
