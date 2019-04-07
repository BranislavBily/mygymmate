package sample.Users.Trainer;


import sample.Users.Trainee.Measurement;
import sample.Users.Trainee.Trainee;

import sample.Users.User;
import sample.Users.UserInfo;


import java.util.ArrayList;

public class Trainer extends User{

    private String dateOfBirth;
    private String email;
    private String phoneNumber;
    private ArrayList<Trainee> trainees;
    private Measurement measurement;
    private UserInfo userInfo;

    public Trainer(String username, String password) {
        super(username, password);
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(ArrayList<Trainee> trainees) {
        this.trainees = trainees;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
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
        return "Trainer{" +
                "dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", trainees=" + trainees +
                ", measurement=" + measurement +
                ", userInfo=" + userInfo +
                '}';
    }
}


