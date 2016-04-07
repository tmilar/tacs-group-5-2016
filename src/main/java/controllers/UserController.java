package controllers;

import model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/user")
    public User userHome(@RequestParam(value="name", defaultValue="superuser") String name) {
        return new User("A default nickname",  name);
    }
}
