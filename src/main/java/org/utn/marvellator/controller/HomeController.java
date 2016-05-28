package org.utn.marvellator.controller;

import org.hibernate.service.spi.InjectService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.utn.marvellator.model.UserSession;


@Controller
public class HomeController {

    @RequestMapping(value = {"/","/home", "/index"})
    public String home() {
        return "index";
    }


}
