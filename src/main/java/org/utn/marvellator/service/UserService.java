package org.utn.marvellator.service;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.utn.marvellator.ContentItem.UserContentitem;
import org.utn.marvellator.model.User;

import java.util.List;


public interface UserService extends
				ApplicationListener<AuthenticationSuccessEvent> {

    void registerUser(User user);

    void deleteUser(User user);

    void loginUser(User user);

    void logoutUser();

    User getUserById(String id);

    User getUserByUserName(String userName);

    User getUserByEmail(String email);

    List<User> getAllUsers();

		UserContentitem getUserContentItemById(String id);

		List<UserContentitem> getAllUsersContentItems();

}
