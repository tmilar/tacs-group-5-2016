package org.utn.marvellator.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.utn.marvellator.model.User;


@Controller
public class HomeController implements ErrorController {

    @RequestMapping(value = {"/", "/index", "/home", "/error"})
    public String home(Model model) {
        model.addAttribute(new User());
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
