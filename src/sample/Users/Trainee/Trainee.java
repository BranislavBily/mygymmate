package sample.Users.Trainee;


import sample.Users.User;
import sample.Users.UserInfo;

public class Trainee extends User {


    private int trainerID;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private Measurement measurement;
    private UserInfo userInfo;

    public Trainee() {}

    public Trainee(String username, String password) {
        super(username, password);
    }


    public Trainee(int trainerID, String dateOfBirth, String email, String phoneNumber, Measurement measurement, UserInfo userInfo) {
        this.trainerID = trainerID;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.measurement = measurement;
        this.userInfo = userInfo;
    }

    public Trainee(String username, String password, int trainerID, String dateOfBirth, String email, String phoneNumber, Measurement measurement, UserInfo userInfo) {
        super(username, password);
        this.trainerID = trainerID;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.measurement = measurement;
        this.userInfo = userInfo;
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

