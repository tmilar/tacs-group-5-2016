package org.utn.marvellator.service;

import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;

import java.util.List;

public interface GroupService {

    Group createGroup(String newGroupName, String creatorName);

    void deleteGroupById(String id);

    Group addCharacterToGroup(MarvelCharacter character, Group group) throws CharacterAlreadyInGroupException;

    boolean removeCharacterFromGroup(MarvelCharacter character, Group group);

    List<MarvelCharacter> getIntersectionCharacters(Group g1, Group g2);
}
