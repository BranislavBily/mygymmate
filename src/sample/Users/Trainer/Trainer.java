package sample.Users.Trainer;

import sample.Users.Trainee.Measurement;
import sample.Users.Trainee.Trainee;
import sample.Users.User;

import java.util.ArrayList;

public class Trainer extends User {

    private ArrayList<Trainee> trainees;
    private Measurement measurement;

    public Trainer(String username, String password) {
        super(username, password);
    }
}
