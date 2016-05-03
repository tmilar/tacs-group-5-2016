package org.utn.marvellator.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.CharacterAlreadyInGroupException;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.model.MarvelCharacter;
import org.utn.marvellator.repository.GroupRepository;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class GroupServiceTest {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    // Fixture data
    private final String testCreatorName = "testUser";
    private final String newGroupName = "test group 1";
    private final MarvelCharacter testCharacter = new MarvelCharacter("superTestMan");

    @After
    public void clean(){
        groupRepository.deleteAll();
    }

    @Test
    public void createGroup_withValidNames_createsAndPersistsANewGroup(){
        Group newGroup = groupService.createGroup(newGroupName, testCreatorName);

        assertEquals(1, groupRepository.count());
        assertEquals(newGroupName, groupRepository.findOne(newGroup.getId()).getName());
    }

    @Test(expected = DuplicateKeyException.class)
    public void createGroup_withRepeatedNamesForSameUser_givesError(){

        groupService.createGroup(newGroupName, testCreatorName);
        groupService.createGroup(newGroupName, testCreatorName);
    }

    @Test
    public void createGroup_withDifferentNamesForSameUser_createsAllGroupsForUser(){
        String newGroup2Name = "test group 2";

        Group newGroup1 = groupService.createGroup(newGroupName, testCreatorName);
        Group newGroup2 = groupService.createGroup(newGroup2Name, testCreatorName);

        assertEquals(2, groupRepository.count());
        assertEquals(newGroupName, groupRepository.findOne(newGroup1.getId()).getName());
        assertEquals(newGroup2Name, groupRepository.findOne(newGroup2.getId()).getName());
    }

    @Test
    public void createGroup_withSameNamesForDifferentUsers_createsAllGroupsForUsers(){
        String testCreator2Name = "testUser2";

        Group newGroup1 = groupService.createGroup(newGroupName, testCreatorName);
        Group newGroup2 = groupService.createGroup(newGroupName, testCreator2Name);

        assertEquals(2, groupRepository.count());
        assertEquals(groupRepository.findOne(newGroup1.getId()).getName(), groupRepository.findOne(newGroup2.getId()).getName());
    }

    @Test
    public void deleteGroup_byExistingId_deletesExistingGroup(){
        Group existing = createTestGroup();
        assertEquals(1, groupRepository.count());
        assertEquals(existing.getId(), groupRepository.findAll().get(0).getId());

        groupService.deleteGroupById(existing.getId());

        assertEquals(0, groupRepository.count());
    }

    @Test
    public void addCharacterToGroup_toAnExistingGroup_addsNewCharacter() throws CharacterAlreadyInGroupException {
        Group existingGroup = createTestGroup();

        groupService.addCharacterToGroup(testCharacter, existingGroup);

        assertEquals(1, existingGroup.getCharacters().size());
        assertTrue(existingGroup.getCharacters().contains(testCharacter));
        assertTrue(groupRepository.findFirstByName(existingGroup.getName()).getCharacters().contains(testCharacter));
    }

    @Test(expected = CharacterAlreadyInGroupException.class)
    public void addCharacterToGroup_withAnExistingCharacterToAnExistingGroup_givesError() throws CharacterAlreadyInGroupException{
        Group existingGroup = createTestGroup();
        existingGroup.addCharacter(testCharacter);
        groupRepository.save(existingGroup);
        assertTrue(groupRepository.findFirstByName(existingGroup.getName()).getCharacters().contains(testCharacter));

        groupService.addCharacterToGroup(testCharacter, existingGroup);
    }

    @Test
    public void addCharacterToGroup_with2DifferentCharacters_addsNewCharacters() throws CharacterAlreadyInGroupException {
        Group existingGroup = createTestGroup();
        MarvelCharacter char1 = new MarvelCharacter("jose");
        MarvelCharacter char2 = new MarvelCharacter("luis");

        groupService.addCharacterToGroup(char1, existingGroup);
        groupService.addCharacterToGroup(char2, existingGroup);

        assertEquals(2, existingGroup.getCharacters().size());
        assertTrue(groupRepository.findFirstByName(existingGroup.getName()).getCharacters().contains(char1));
        assertTrue(groupRepository.findFirstByName(existingGroup.getName()).getCharacters().contains(char2));
    }

    /**
     * Generate and persist a test group with random group_name and creator_name
     * @return the created group
     */
    private Group createTestGroup(){
        Group testGroup = new Group("testGroupName" + UUID.randomUUID(), "testUsername" + UUID.randomUUID());
        return groupRepository.insert(testGroup);
    }

}
