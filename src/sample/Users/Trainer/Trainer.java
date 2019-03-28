package sample.Users.Trainer;

import sample.Users.GymGoer;
import sample.Users.Trainee.Measurement;
import sample.Users.Trainee.Trainee;

import java.util.ArrayList;

public class Trainer extends GymGoer {

    private ArrayList<Trainee> trainees;
    private Measurement measurement;

    public Trainer(String username, String password) {
        super(username, password);
    }
}
