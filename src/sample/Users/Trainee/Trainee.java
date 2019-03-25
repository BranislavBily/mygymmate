package sample.Users.Trainee;

import sample.Users.User;

public class Trainee extends User {

    private int trainerID;
    private Measurement measurement;

    public Trainee() {}

    public Trainee(String username, String password) {
        super(username, password);
    }
}
