package marvellator;

public class User {

    private String nickName;
    private String userName;

    public User(String nickName, String userName) {
        this.nickName = nickName;
        this.userName = userName;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
