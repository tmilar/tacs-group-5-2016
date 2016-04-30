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
        Group testGroup = new Group(testName);

        groupRepository.save(testGroup);

        assertEquals(1, groupRepository.count());
        assertEquals(testName, groupRepository.findFirstByName(testName).getName());
    }

    @Test
    public void shouldModifyAndPersistAGroupNameChange(){
        Group testGroup = new Group("testName1");
        groupRepository.save(testGroup);

        String testName2 = "testName2";
        testGroup.setName(testName2);
        groupRepository.save(testGroup);

        assertEquals(1, groupRepository.count());
        assertEquals(testName2, groupRepository.findFirstByName(testName2).getName());
    }

    @Test
    public void shouldDeleteAndNoLongerPersistGroup() {
        Group testGroup = new Group();

        groupRepository.save(testGroup);
        groupRepository.delete(testGroup);

        assertEquals(0, groupRepository.count());
    }

    @Test
    public void shouldPersistCharacterInAGroup() {
        String testName = "testGroup";
        int testCharacter = 666;

        Group testGroup = new Group(testName);
        testGroup.addCharacter(testCharacter);
        testGroup.addCharacter(testCharacter);

        groupRepository.save(testGroup);

        assertEquals(1, groupRepository.findFirstByName(testName).getCharacters().size());
        assertTrue(groupRepository.findFirstByName(testName).getCharacters().contains(testCharacter));
    }

    @Test
    public void shouldDeleteAndNoLongerPersistCharacterInAGroup() {
        String testName = "testGroup";
        int testCharacter = 666;

        Group testGroup = new Group(testName);
        testGroup.addCharacter(testCharacter);
        groupRepository.save(testGroup);

        testGroup.removeCharacter(testCharacter);
        groupRepository.save(testGroup);

        assertEquals(1, groupRepository.count());
        assertEquals(0, groupRepository.findFirstByName(testName).getCharacters().size());
    }
}
