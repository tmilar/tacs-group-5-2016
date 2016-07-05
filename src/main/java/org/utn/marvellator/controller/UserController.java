package org.utn.marvellator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.ContentItem.UserContentitem;
import org.utn.marvellator.model.SignupForm;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.GroupService;
import org.utn.marvellator.service.UserService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

	@Autowired
	CurrentUserDetailsService currentUserDetailsService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User userHome(@RequestParam(value = "name", defaultValue = "A default name :)") String name) {
        return new User("A default nickname. We will only be receiving a userName. It is: ", name, "pass");
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void userSignUp(@ModelAttribute("userName") String userName, @ModelAttribute("name") String name, @ModelAttribute("password") String password) {
        userService.registerUser(new User(name, userName, password));
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String userPage(@PathVariable("id") String id, Model model) {
			User current = userService.getUserById(id);
        UserContentitem user = userService.getUserContentItemById(id);
			if(user != null){
            model.addAttribute("user",user);
            model.addAttribute("groups",groupService.getGroupsByCreator(current));
        }
        return "userDetails";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute(new SignupForm());
        return "signup";
    }

		@RequestMapping(value = "/users", method = RequestMethod.GET)
		public String getUsers(Model model) {
			model.addAttribute("users", userService.getAllUsersContentItems());
			return "users";
		}

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {
        if (errors.hasErrors()) {
            return "signup";
        }

        userService.registerUser(new User(signupForm.getUserName(), signupForm.getUserName(), signupForm.getPassword()) );
        //TODO show somehow a message saying that the user has been registered...
        return "redirect:/home";
    }
}
