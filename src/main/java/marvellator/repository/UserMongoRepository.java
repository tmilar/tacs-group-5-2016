package marvellator.repository;


import marvellator.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

    List<User> findById(final String id);

}
