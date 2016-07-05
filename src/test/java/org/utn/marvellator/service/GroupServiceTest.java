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

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        assertTrue(_characterPersistedInGroup(testCharacter, existingGroup));
    }

    @Test(expected = CharacterAlreadyInGroupException.class)
    public void addCharacterToGroup_withAnExistingCharacterToAnExistingGroup_givesError() throws CharacterAlreadyInGroupException{
        Group existingGroup = createTestGroupWithTestCharacter();
        assertTrue(_characterPersistedInGroup(testCharacter, existingGroup));

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
        assertTrue(_characterPersistedInGroup(char1, existingGroup));
        assertTrue(_characterPersistedInGroup(char2, existingGroup));
    }

    @Test
    public void deleteCharacterFromGroup_withAnExistingCharacter_removesTheCharacter() throws CharacterAlreadyInGroupException {
        Group groupWithCharacter = createTestGroupWithTestCharacter();
        assertTrue(_characterPersistedInGroup(testCharacter, groupWithCharacter));

				Group removed = groupService.removeCharacterFromGroup(testCharacter, groupWithCharacter);

        assertTrue(removed != null);
        assertFalse(_characterPersistedInGroup(testCharacter, groupWithCharacter));
    }

    @Test
    public void getIntersectionCharacters_with2groupsWithCharacters_shouldReturnCharactersInBothGroups() throws CharacterAlreadyInGroupException {

        Group group1 = createTestGroup();
        Group group2 = createTestGroup();

        MarvelCharacter char1 =  new MarvelCharacter("char_1");
        MarvelCharacter char2 =  new MarvelCharacter("char_2");
        MarvelCharacter char3 =  new MarvelCharacter("char_3");
        MarvelCharacter char4 =  new MarvelCharacter("char_4");
        MarvelCharacter char5 =  new MarvelCharacter("char_5");

        // g1 characters
        group1.addCharacter(char1);
        group1.addCharacter(char2);
        group1.addCharacter(char3);

        // g2 different
        group2.addCharacter(char4);
        group2.addCharacter(char5);

        // g2 same - intersection
        group2.addCharacter(char1);
        group2.addCharacter(char3);


        List<MarvelCharacter> intersectionCharacters = groupService.getIntersectionCharacters(group1, group2);

        assertEquals(2, intersectionCharacters.size());
        assertTrue(intersectionCharacters.contains(char1));
        assertTrue(intersectionCharacters.contains(char3));

    }

    /**
     * Create and persist a test group with a test character
     *
     * @return the created group
     * @throws CharacterAlreadyInGroupException
     */
    private Group createTestGroupWithTestCharacter() throws CharacterAlreadyInGroupException {
        Group test = createTestGroup();
        test.addCharacter(testCharacter);
        return groupRepository.save(test);
    }

    /**
     * Utility method to help assert if a character is in a group
     *
     * @param character character wanted to be found
     * @param group that may contain the character
     * @return true if char is actually in group
     */
    private Boolean _characterPersistedInGroup(MarvelCharacter character, Group group) {
        return groupRepository.findOne(group.getId()).getCharacters().contains(character);
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
