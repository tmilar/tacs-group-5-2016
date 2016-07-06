package org.utn.marvellator.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.utn.marvellator.ContentItem.GroupCharacter;
import org.utn.marvellator.model.*;
import org.utn.marvellator.repository.GroupRepository;
import org.utn.marvellator.service.GroupService;
import org.utn.marvellator.service.impl.CurrentUserDetailsService;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public HttpEntity<Group> createGroup(@RequestBody GroupWrapper group) {
        User user = currentUserDetailsService.getCurrentUser();
			  Group g = groupService.createGroup(group.getName(), user.getUserName());
				return new ResponseEntity<Group>( g, HttpStatus.CREATED ) ;
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

		@RequestMapping(value = "/{groupId}/characters", method = RequestMethod.GET)
		public List<MarvelCharacter> getCharacter(@PathVariable String groupId) throws CharacterAlreadyInGroupException {
			String username = currentUserDetailsService.getCurrentUsername();
			Group group = groupRepository.findFirstById(groupId);

			if (group != null){
				//TODO Add friendly message for user in case of system errors
				try {
					return group.getCharacters();
				} catch (Exception e){
					//Something
				}
			}

			return new ArrayList<>();
		}

		@RequestMapping(value = "/{groupId}/availableCharacters", method = RequestMethod.GET)
		public List<GroupCharacter> getAvailableCharacters(@PathVariable String groupId) throws CharacterAlreadyInGroupException {
			String username = currentUserDetailsService.getCurrentUsername();
			Group group = groupRepository.findFirstById(groupId);

			if (group != null){
				//TODO Add friendly message for user in case of system errors
				try {
					List<GroupCharacter> charactersForGroup = groupService.getAvailableCharactersForGroup(group, 0);
					return charactersForGroup;
				} catch (Exception e){
					//Something
				}
			}

			return new ArrayList<>();
		}

		@RequestMapping(value = "/{groupId}/availableCharacters/{page}", method = RequestMethod.GET)
		public List<GroupCharacter> getCharacterPage(@PathVariable String groupId, @PathVariable(value = "page") Integer page) throws CharacterAlreadyInGroupException {
			String username = currentUserDetailsService.getCurrentUsername();
			Group group = groupRepository.findFirstById(groupId);

			if (group != null){
				//TODO Add friendly message for user in case of system errors
				try {
					List<GroupCharacter> charactersForGroup = groupService.getAvailableCharactersForGroup(group, page);
					return charactersForGroup;
				} catch (Exception e){
					//Something
				}
			}

			return new ArrayList<>();
		}

    @RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
    public HttpEntity<Group> addCharacter(@PathVariable String groupId,
                                                   @RequestBody MarvelCharacter character) throws CharacterAlreadyInGroupException {
				String username = currentUserDetailsService.getCurrentUsername();
        Group group = groupRepository.findFirstById(groupId);

			if(group != null){
            MarvelCharacter newCharacter = new MarvelCharacter(character.getName(), character.getMarvelId());
            Group updated = groupService.addCharacterToGroup(newCharacter, group);
            ResponseEntity<Group> response = new ResponseEntity<Group>( updated, HttpStatus.CREATED ) ;

            return response;
        }

        return new ResponseEntity<Group>( HttpStatus.NOT_FOUND );
    }

		@RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
		public HttpEntity<Group> removeCharacter(@PathVariable String groupId,
																					@RequestBody MarvelCharacter character) throws CharacterAlreadyInGroupException {
			String username = currentUserDetailsService.getCurrentUsername();
			Group group = groupRepository.findFirstById(groupId);

			if (group != null){
				MarvelCharacter newCharacter = new MarvelCharacter(character.getName(), character.getMarvelId());
				Group updated = groupService.removeCharacterFromGroup(newCharacter, group);
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
		@PreAuthorize("hasAuthority('ADMIN')")
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
