package org.utn.marvellator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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

    @Test
    public void createGroup_withValidNames_createsAndPersistsANewGroup(){
        String newGroupName = "test group 1";
        String creatorName = "testUser";

        Group newGroup = groupService.createGroup(newGroupName, creatorName);

        assertEquals(1, groupRepository.count());
        assertEquals(newGroupName, groupRepository.findOne(newGroup.getId()).getName());
    }
}
