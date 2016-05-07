package org.utn.marvellator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.utn.marvellator.model.Group;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<Group, String>{

    Group findFirstByNameAndCreator(String groupName, String userName);
    List<Group> findByCreator(String userName);
}
