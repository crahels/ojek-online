package classes;

public class UserDriver {
    private int userId;
    private String userName;
    private String userUsername;

    public UserDriver(int userId, String userName, String userUsername) {
        this.userId = userId;
        this.userName = userName;
        this.userUsername = userUsername;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserUsername() {
        return userUsername;
    }
}
