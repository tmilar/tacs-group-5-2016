package org.utn.marvellator.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.utn.marvellator.model.User;


@Controller
public class HomeController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public String home(Model model) {
        model.addAttribute(new User());
        return "/index";
    }

    @RequestMapping(value = ERROR_PATH )
    public String onErrorRedirect() {
        //TODO print an error msg in index?
        return "redirect:/index";
    }

}
