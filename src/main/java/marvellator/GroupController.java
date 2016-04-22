package marvellator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
    /* API */
    @RequestMapping(value = "api/groups", method = RequestMethod.GET)
    public String groups() {
        return "List of groups \n";
    }

    @RequestMapping(value = "api/groups", method = RequestMethod.POST)
    public String newGroup() {
        return "New group \n";
    }

    @RequestMapping(value = "api/groups/{id}", method = RequestMethod.GET)
    public String group(@PathVariable int id) {
        return "A group \n";
    }

    @RequestMapping(value = "api/groups/{id}", method = RequestMethod.PUT)
    public String addCharacter(@PathVariable int id) {
        return "Character added to a group \n";
    }

    @RequestMapping(value = "api/groups/{id}", method = RequestMethod.DELETE)
    public String deleteGroup(@PathVariable int id) {
        return "Group deleted \n";
    }

    /* FRONT END */
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String groupsPage() {
        return "Groups Page \n";
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public String groupPage(@PathVariable int id) {
        return "A group Page \n";
    }
}
