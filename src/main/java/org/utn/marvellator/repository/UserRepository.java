package org.utn.marvellator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utn.marvellator.model.User;

public interface UserRepository extends MongoRepository<User, String>{

    User findFirstByUserName(String userName);

}
