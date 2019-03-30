package sample.Users.Trainee;


import sample.Users.User;
import sample.Users.UserInfo;


import sample.Users.GymGoer;
import sample.Users.TypeOfTraining;

public class Trainee extends GymGoer {


    private int trainerID;
    private Measurement measurement;
    private UserInfo userInfo;

    public Trainee() {}

    public Trainee(String username, String password) {
        super(username, password);
    }



    public Trainee(String username, String password, String gender, double weight, double height, double recommendedCalories, double recommendedProtein, double recommendedWater, TypeOfTraining typeOfTraining, int trainerID, Measurement measurement) {
        super(username, password, gender, weight, height, recommendedCalories, recommendedProtein, recommendedWater, typeOfTraining);
        this.trainerID = trainerID;
        this.measurement = measurement;
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

