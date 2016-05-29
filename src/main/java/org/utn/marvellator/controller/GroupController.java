package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.GroupService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

@Controller
public class GroupController {

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @Autowired
    GroupService groupService;

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public String groupById(@PathVariable String id, Model model) {
        User user = currentUserDetailsService.getCurrentUser();
        model.addAttribute("group", groupService.getGroupById(user, id));
        return "group-details";
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String groups(Model model) {
        User user = currentUserDetailsService.getCurrentUser();
        model.addAttribute("group", new Group());
        model.addAttribute("groups", groupService.getGroupsByCreator(user));
        return "groups";
    }


}
