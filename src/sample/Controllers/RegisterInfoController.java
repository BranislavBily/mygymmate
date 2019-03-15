package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.User;

public class RegisterInfoController {

    private User user;

    @FXML
    private Label labelUsername;

    void setUser(User user) {
        this.user = user;
        System.out.println(user.toString());
    }

    void setLabelUsername() {
        labelUsername.setText("Hi "+user.getUsername() + "!");
    }
}
