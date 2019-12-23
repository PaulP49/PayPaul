package de.oth.PayPaul.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
  public String login(Model model){

    return "loginNew";
  }

}
