package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.User;
import org.utn.marvellator.repository.UserRepository;
import org.utn.marvellator.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Validate and register a new user.
     *
     * @param user
     */
    public void registerUser(User user) {
        checkExistingUsername(user);

        user.setPassword(user.getPassword() + "secret"); //TODO actually encrypt password

        userRepository.save(user);
    }

    private void checkExistingUsername(User user) {
        User existingUser = userRepository.findFirstByUserName(user.getUserName());

        if(existingUser != null ){
            throw new UserAlreadyExistsException(user.getUserName());
        }
    }
}
