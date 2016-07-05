package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.utn.marvellator.ContentItem.UserContentitem;
import org.utn.marvellator.model.*;
import org.utn.marvellator.repository.UserRepository;
import org.utn.marvellator.service.GroupService;
import org.utn.marvellator.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

		@Autowired
		GroupService groupService;

	@Autowired
	private UserSession loggedUser;

		@Autowired
		CurrentUserDetailsService currentUserDetailsService;

    /**
     * Validate and register a new user.
     *
     * @param user user that wants to register
     */
    public void registerUser(User user) {
        if (checkExistingUsername(user))
            throw new UserAlreadyExistsException(user.getUserName());

        user.setPassword( new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        user = userRepository.findFirstByUserName(user.getUserName());
        String id = user.getId();
        userRepository.delete(id);
    }

    private boolean checkExistingUsername(User user) {
        User existingUser = userRepository.findFirstByUserName(user.getUserName());

        return existingUser != null;
    }

    /**
     * Login an existing user
     *
     * @param user user that wants to log in
     */
    public void loginUser(User user) {
        User existingUser = userRepository.findFirstByUserName(user.getUserName());
        if (!existingUser.getPassword().equals(user.getPassword())){
            throw new UserDoesNotExistException(user.getUserName());
        }

        /*Logged user is set in this singleton until spring security is totally configured*/
        Session.getInstance().setCurrentSession(user);
        loggedUser.setUser(user);

        user.setLastLogin(new Date());
        userRepository.save(user);
    }
    public void logoutUser(){
        loggedUser.clean();
    }


    public User getUserById(String id) {
        return userRepository.findOne(id);
    }

    public User getUserByUserName(String userName){
        return userRepository.findFirstByUserName(userName);
    }

    public User getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

		public UserContentitem getUserContentItemById(String id) {
			User u = userRepository.findFirstById(id);
			return new UserContentitem(u.getId(), u.getName(), u.getUserName(), u.getEmail(), u.getLastLogin(), groupService.getGroupsByCreator(currentUserDetailsService.getCurrentUser()).size(), u.getFavorites().size());
		}

		public List<UserContentitem> getAllUsersContentItems() {
			List<User> users = userRepository.findAll();
			List<UserContentitem> usersCI = new ArrayList<>();
			for (User u : users){
				UserContentitem uci = new UserContentitem(u.getId(), u.getName(), u.getUserName(), u.getEmail(), u.getLastLogin(), groupService.getGroupsByCreator(currentUserDetailsService.getCurrentUser()).size(), u.getFavorites().size());
				usersCI.add(uci);
			}
			return usersCI;
		}

}
