package de.oth.PayPaul.ui.controller;

import de.oth.PayPaul.persistence.model.Account;
import de.oth.PayPaul.service.implementation.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountController {

  AccountService accountService;

  @Autowired
  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String createTestUser() {
    Account testAccount = new Account();
    testAccount.setEmail("null@dev.de");
    testAccount.setCredit(80);
    testAccount.setFirstName("dev");
    testAccount.setLastName("yo");
    testAccount.setPasswordHash("test");
    accountService.saveNewAccount(testAccount);
    return "success";
  }
}
