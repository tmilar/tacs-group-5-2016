package org.utn.marvellator.model;

public class Session {
    private static Session session = new Session();
    private User currentSession;

    public static Session getInstance() {
        return session;
    }

    private Session() {
    }

    public void setCurrentSession(User currentSession) {
        this.currentSession = currentSession;
    }
}
