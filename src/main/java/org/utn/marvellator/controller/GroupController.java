package org.utn.marvellator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GroupController {

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public String groupById(@PathVariable int id) {
        return "group-details";
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String groups() {

        return "groups";
    }


}
