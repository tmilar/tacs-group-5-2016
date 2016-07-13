package org.utn.marvellator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.utn.marvellator.model.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

    User findFirstByUserName(String userName);
    User findFirstByEmail(String email);
		User findFirstById(String id);
}
