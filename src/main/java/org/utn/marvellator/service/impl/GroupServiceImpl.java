package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.repository.GroupRepository;
import org.utn.marvellator.service.GroupService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

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
    public boolean removeCharacterFromGroup(MarvelCharacter character, Group group) {
        boolean removed = group.removeCharacter(character);
        groupRepository.save(group);
        return removed;
    }

    @Override
    public List<MarvelCharacter> getIntersectionCharacters(Group g1, Group g2) {

        List<MarvelCharacter> g1Characters = g1.getCharacters();
        List<MarvelCharacter> g2Characters = g2.getCharacters();

        List<MarvelCharacter> intersectionCharacters =  g1Characters.stream()
                                                            .filter( g1char -> g2Characters.contains(g1char))
                                                            .collect(Collectors.toList());

        return intersectionCharacters;
    }
}
