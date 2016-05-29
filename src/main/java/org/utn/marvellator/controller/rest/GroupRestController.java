package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.*;
import org.utn.marvellator.repository.GroupRepository;
import org.utn.marvellator.service.GroupService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/groups")
public class GroupRestController {

    @Autowired
    private CurrentUserDetailsService currentUserDetailsService;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/{userName}/{groupName}", method = RequestMethod.GET)
    public Group groupByUserAndName(@PathVariable String userName, @PathVariable String groupName) {
        Group group = groupRepository.findFirstByNameAndCreator(groupName, userName);

        return group;
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public List<Group> groupsByUser(@PathVariable String userName) {
        List<Group> groups = groupRepository.findByCreator(userName);

        return groups;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Group> groups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createGroup(@RequestBody GroupWrapper group) {
        User user = currentUserDetailsService.getCurrentUser();
        groupService.createGroup(group.getName(), user.getUserName());
    }

    @RequestMapping(value = "/{userName}/{groupName}", method = RequestMethod.PUT)
    public HttpEntity<Group> addCharacter(@PathVariable String userName,
                                                   @PathVariable String groupName,
                                                   @RequestBody MarvelCharacter character) throws CharacterAlreadyInGroupException {
        Group group = groupRepository.findFirstByNameAndCreator(groupName, userName);

        if(group != null){
            MarvelCharacter newCharacter = new MarvelCharacter(character.getName());
            Group updated = groupService.addCharacterToGroup(newCharacter, group);
            ResponseEntity<Group> response = new ResponseEntity<Group>( updated, HttpStatus.CREATED ) ;

            return response;
        }

        return new ResponseEntity<Group>( HttpStatus.NOT_FOUND );
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void deleteGroup(@RequestBody GroupWrapper group) {
        groupService.deleteGroupById(group.getId());
    }

    // TODO:1 assign this only to ADMIN role
    // TODO:2 review actual API/endpoint for this... is OK this way or maybe pass the 2nd group as a path parameter?
    //    ie. /{idgroup1}?intersect={idgroup2} // maybe better?
    @RequestMapping(value = "/{idGroup1}/intersection/{idGroup2}", method = RequestMethod.GET)
    public ResponseEntity<List<MarvelCharacter>> groupsIntersection(@PathVariable String idGroup1,
                                                                    @PathVariable String idGroup2) {
        Group group1 = groupRepository.findOne(idGroup1);
        Group group2 = groupRepository.findOne(idGroup2);

        if (group1 == null || group2 == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }

        List<MarvelCharacter> intersection = groupService.getIntersectionCharacters(group1, group2);

        return new ResponseEntity<>(intersection, HttpStatus.OK);
    }
}
