package org.utn.marvellator.service;

import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.impl.CurrentUserIsNotTheCreatorException;

import java.util.List;

public interface GroupService {

    Group createGroup(String newGroupName, String creatorName);

    void deleteGroupById(String id);

    Group addCharacterToGroup(MarvelCharacter character, Group group) throws CharacterAlreadyInGroupException;

    boolean removeCharacterFromGroup(MarvelCharacter character, Group group);

    List<Group> getGroupsByCreator(User user);

    Group getGroupById(User user, String id) throws CurrentUserIsNotTheCreatorException;

    List<MarvelCharacter> getIntersectionCharacters(Group g1, Group g2);
}
