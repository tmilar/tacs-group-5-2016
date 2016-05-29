package org.utn.marvellator.model;


import org.springframework.stereotype.Component;

@Component
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    private User user;

    public UserSession() {

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void clean(){
        user = new User();
    }
}