package org.utn.marvellator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.utn.marvellator.model.User;

/**
 * Created by Tomas on 4/20/2016.
 */

public interface UserRepository extends MongoRepository<User, String>{
}
