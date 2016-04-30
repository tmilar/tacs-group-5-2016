package org.utn.marvellator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utn.marvellator.model.Group;

public interface GroupRepository extends MongoRepository<Group, String>{

    Group findFirstById(String id);
    Group findFirstByName(String name);

}
