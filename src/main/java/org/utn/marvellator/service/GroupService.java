package org.utn.marvellator.service;

import org.utn.marvellator.ContentItem.GroupCharacter;
import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.impl.CurrentUserIsNotTheCreatorException;

import java.io.IOException;
import java.util.List;

public interface GroupService {

    Group createGroup(String newGroupName, String creatorName);

    void deleteGroupById(String id);

    Group addCharacterToGroup(MarvelCharacter character, Group group) throws CharacterAlreadyInGroupException;

		Group removeCharacterFromGroup(MarvelCharacter character, Group group);

    List<Group> getGroupsByCreator(User user);

    Group getGroupById(User user, String id) throws CurrentUserIsNotTheCreatorException;

		List<GroupCharacter> getAvailableCharactersForGroup(Group group, Integer page) throws IOException;

    List<MarvelCharacter> getIntersectionCharacters(Group g1, Group g2);
}
