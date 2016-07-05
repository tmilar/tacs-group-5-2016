package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.ContentItem.GroupCharacter;
import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.GroupRepository;
import org.utn.marvellator.service.CharacterService;
import org.utn.marvellator.service.GroupService;
import org.utn.marvellator.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

		@Autowired
		private CharacterService characterService;

		@Autowired
		private UserService userService;


    @Override
    public Group createGroup(String newGroupName, String creatorName) {
        Group group = new Group(newGroupName, creatorName);

        return groupRepository.insert(group);
    }

    @Override
    public void deleteGroupById(String id) {
        groupRepository.delete(id);
    }

    @Override
    public Group addCharacterToGroup(MarvelCharacter character, Group group) throws CharacterAlreadyInGroupException {
        group.addCharacter(character);
        return groupRepository.save(group);
    }

    @Override
    public Group getGroupById(User user, String groupId) throws CurrentUserIsNotTheCreatorException{
        Group group = groupRepository.findFirstById(groupId);
        if(!group.getCreator().equals(user.getUserName())){
            throw new CurrentUserIsNotTheCreatorException();
        }
        return group;
    }

    @Override
    public List<Group> getGroupsByCreator(User user){
        return groupRepository.findByCreator(user.getUserName());
    }

    @Override
    public Group removeCharacterFromGroup(MarvelCharacter character, Group group) {
        boolean removed = group.removeCharacter(character);
        groupRepository.save(group);
        return group;
    }

    @Override
    public List<MarvelCharacter> getIntersectionCharacters(Group g1, Group g2) {

        List<MarvelCharacter> g1Characters = g1.getCharacters();
        List<MarvelCharacter> g2Characters = g2.getCharacters();

        return g1Characters.stream().filter(g2Characters::contains).collect(Collectors.toList());
		}

		@Override
		public List<GroupCharacter> getAvailableCharactersForGroup(Group group, Integer page) throws IOException {
			List<MarvelCharacter> characters = characterService.charactersPage(page);
			List<GroupCharacter> groupCharacters = new ArrayList<>();
			for (MarvelCharacter character : characters){
				boolean doesntExist = true;
				for (MarvelCharacter groupCharacter : group.getCharacters()){
					if (character.getMarvelId().equals(groupCharacter.getMarvelId()) && character.getName().equals(groupCharacter.getName())) {
						doesntExist = false;
						break;
					}
				}
				if (doesntExist)
					groupCharacters.add(new GroupCharacter(character.getName(), character.getMarvelId(), false));
			}
			return groupCharacters;
		}

	@Override
	public List<Group> getAllGroupsFromAllCharacters() {
		List<User> users = userService.getAllUsers();
		List<Group> groups = new ArrayList<>();
		for (User u : users){
			groups.addAll(groupRepository.findByCreator(u.getUserName()));
		}
		return groups;
	}
}
