package org.utn.marvellator.service;

import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;

public interface GroupService {

    Group createGroup(String newGroupName, String creatorName);

    void deleteGroupById(String id);

    void addCharacterToGroup(MarvelCharacter character, Group existingGroup) throws CharacterAlreadyInGroupException;
}
