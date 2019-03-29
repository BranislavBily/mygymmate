package sample.Users.Trainee;

import sample.Users.User;
import sample.Users.UserInfo;

public class Trainee extends User {

    private int trainerID;
    private Measurement measurement;
    private UserInfo userInfo;

    public Trainee() {}

    public Trainee(String username, String password) {
        super(username, password);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        super.toString();
        return "Trainee{" +
                "trainerID=" + trainerID +
                ", measurement=" + measurement +
                ", userInfo=" + userInfo.toString() +
                '}';
    }
}