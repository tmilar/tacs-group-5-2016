package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.UserService;
import javax.validation.Valid;


@Controller
@SessionAttributes("loggedUser")
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute User user){
        return "characters";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        userService.logoutUser();
        return "redirect:/home";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new User());
        return "login";
    }

}
