package OjekServices;

public class UserDOO {
    private String id;
    private String username;
    private String phone_number;
    private String status;

    public UserDOO(String username, String phone_number) {
        this.username = username;
        this.phone_number = phone_number;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String id) { this.phone_number = phone_number; }

    public void setStatus(String id) { this.status = status; }

}
