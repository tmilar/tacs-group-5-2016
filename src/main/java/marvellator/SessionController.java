package marvellator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    /* API */
    @RequestMapping(value="api/login",method= RequestMethod.POST)
    public String login() {
        return "marvellator.User logged in";
    }
    @RequestMapping(value="api/logout",method= RequestMethod.POST)
    public String logout() {
        return "marvellator.User signed out";
    }

    /* FRONT END */
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String loginPage() {
        return "Login Page";
    }
    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logoutPage() {
        return "Logout Page";
    }
}
