package sample.Users.Trainer;

import sample.Users.Trainee.Measurement;
import sample.Users.Trainee.Trainee;
import sample.Users.User;
import sample.Users.UserInfo;

import java.util.ArrayList;

public class Trainer extends User {

    private ArrayList<Trainee> trainees;
    private Measurement measurement;
    private UserInfo userInfo;

    public Trainer(String username, String password) {
        super(username, password);
    }
}
