package marvellator;

import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {
    /* API */
    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login() {
        String id = "asdsfdsf";
        return id;
    }
    @RequestMapping(value="api/logout",method= RequestMethod.POST)
    public String logout() {
        return "marvellator.User signed out";
    }

    /* FRONT END */
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String loginPage(@RequestParam(value = "id", defaultValue = "some user's id") String id) {
        return id;
    }
    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logoutPage() {
        return "Logout Page";
    }
}
