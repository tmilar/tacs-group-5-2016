package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.Session;
import org.utn.marvellator.model.User;
import org.utn.marvellator.model.UserSession;
import org.utn.marvellator.repository.UserRepository;
import org.utn.marvellator.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSession loggedUser;

    /**
     * Validate and register a new user.
     *
     * @param user user that wants to register
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

    /**
     * Login an existing user
     *
     * @param user user that wants to log in
     */
    public void loginUser(User user) {
        Session.getInstance().setCurrentSession(user);
        loggedUser.setUser(user);

    }
    public void logoutUser(){
        loggedUser.clean();
    }




}
