package marvellator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }
}
