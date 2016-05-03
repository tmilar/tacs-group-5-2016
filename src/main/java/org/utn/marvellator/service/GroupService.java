package org.utn.marvellator.service;

import org.utn.marvellator.model.Group;

public interface GroupService {

    Group createGroup(String newGroupName, String creatorName);

}
