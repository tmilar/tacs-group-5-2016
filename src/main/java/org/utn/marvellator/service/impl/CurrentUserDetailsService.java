package org.utn.marvellator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.utn.marvellator.model.CurrentUser;
import org.utn.marvellator.model.User;
import org.utn.marvellator.service.UserService;

import static java.util.Objects.isNull;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    public CurrentUserDetailsService() {
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public User getCurrentUser(){
        String username = getCurrentUsername();
        return userService.getUserByUserName(username);
    }

    @Override
    public CurrentUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(userName);
        if (isNull(user))
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", userName));
        return new CurrentUser(user);
    }


}