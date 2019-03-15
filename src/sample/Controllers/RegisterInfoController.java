package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import sample.User;

public class RegisterInfoController {

    private User user;

    @FXML
    private Label labelUsername;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    void setUser(User user) {
        this.user = user;
        System.out.println(user.toString());
    }

    void setLabelUsername() {
        labelUsername.setText("Hi "+user.getUsername() + "!");
    }

    void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "male", "female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose weigh", "Gain muscle")
        );
    }
}
