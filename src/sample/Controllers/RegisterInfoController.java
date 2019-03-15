package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.User;

import javax.annotation.Resources;
import java.net.URL;

public class RegisterInfoController {

    private User user;

    @FXML
    private Label labelUsername;

    void setUser(User user) {
        this.user = user;
        System.out.println(user.toString());
    }

    @FXML
    protected void initialize(URL location, Resources resources) {
        labelUsername.setText(user.getUsername());
    }

}
