package org.utn.marvellator.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.utn.marvellator.ApplicationTest;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.repository.GroupRepository;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
public class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void shouldCreateAndPersistGroup() {
        String testName = "testGroup";
        int testCharacter = 666;

        Group testGroup = new Group(testName);
        testGroup.addCharacter(testCharacter);
        testGroup.addCharacter(testCharacter);

        groupRepository.save(testGroup);

        assertEquals(1, groupRepository.count());
        assertEquals(testName, groupRepository.findFirstByName(testName).getName());
        assertEquals(1, groupRepository.findFirstByName(testName).getCharacters().size());
        assertTrue(groupRepository.findFirstByName(testName).getCharacters().contains(testCharacter));
    }
}
