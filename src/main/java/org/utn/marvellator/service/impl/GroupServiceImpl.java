package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.Group;
import org.utn.marvellator.repository.GroupRepository;
import org.utn.marvellator.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group createGroup(String newGroupName, String creatorName) {
        Group group = new Group(newGroupName, creatorName);

        groupRepository.insert(group);

        return group;
    }

    @Override
    public void deleteGroupById(String id) {
        groupRepository.delete(id);
    }
}
