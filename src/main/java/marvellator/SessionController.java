package marvellator;

import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {
    /* API */
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login() {
        return "Login page (API) \n";
    }

    @RequestMapping(value = "api/logout", method = RequestMethod.POST)
    public String logout() {

        return "User signed out \n";
    }

    /* FRONT END */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "id", defaultValue = "some user's id") String id) {
        return "Login page, the id is: " + id + "\n";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {

        return "Logout Page \n";
    }
}
