package org.utn.marvellator.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }

}
