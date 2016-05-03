package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.repository.GroupRepository;
import org.utn.marvellator.service.GroupService;

import java.util.List;

@RestController
@RequestMapping(value = "api/groups")
public class GroupRestController {

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

    @RequestMapping(value = "/{userName}/{groupName}", method = RequestMethod.POST)
    public Group createGroup(@PathVariable String userName,
                           @PathVariable String groupName) {
        Group group = groupService.createGroup(groupName, userName);

        return group;
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

    @RequestMapping(value = "/{userName}/{groupName}", method = RequestMethod.DELETE)
    public String deleteGroup(@PathVariable String userName,
                              @PathVariable String groupName) {
        Group group = groupRepository.findFirstByNameAndCreator(groupName, userName);


        if(group != null){
            groupRepository.delete(group);
            return "" + group +" deleted.";
        }else return "" + groupName + " not found for user " + userName;
    }
}
