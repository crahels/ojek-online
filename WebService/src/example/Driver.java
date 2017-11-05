package example;

import java.io.Serializable;

public class Driver implements Serializable {
    private int user_id;
    private String name;
    private String profile_picture;
    private int vote;
    private float rating;

    public int getUserId() {
        return user_id;
    }
    public String getUserName() {
        return name;
    }
    public String getProfPic() {
        return profile_picture;
    }
    public int getVote() {
        return vote;
    }
    public float getRating() {
        return rating;
    }
    public  void setUserId(int user_id) {
        this.user_id = user_id;
    }
    public void setUserName(String name) {
        this.name = name;
    }
    public void setProfPic(String profPic) {
        this.profile_picture = profPic;
    }
    public void setVote(int vote) {
        this.vote = vote;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public static Driver setDriverParam(int user_id, String name, String profile_picture, int vote, float rating) {
        Driver d = new Driver();
        d.setUserId(user_id);
        d.setUserName(name);
        d.setProfPic(profile_picture);
        d.setVote(vote);
        d.setRating(rating);
        return d;
    }
}
