package org.utn.marvellator.service;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.repository.GroupRepository;

import static org.junit.Assert.assertEquals;

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


}
