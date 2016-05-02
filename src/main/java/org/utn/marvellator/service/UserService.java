package org.utn.marvellator.service;

import org.utn.marvellator.model.User;
import org.utn.marvellator.model.UserSession;

public interface UserService {

    void registerUser(User user);

    void loginUser(User user);

    void logoutUser();
}
