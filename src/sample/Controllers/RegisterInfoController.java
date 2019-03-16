package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;
import sample.Moduls.ModulFXML;

import sample.Moduls.ModuleTitles;
import sample.User;

import java.awt.event.ActionEvent;

public class RegisterInfoController extends Controller {

    private User user;
    @FXML
    private Button goBackButton;
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

        labelUsername.setText("Hi "+user.getUsername() + " !");
        labelUsername.setLayoutX(182-((user.getUsername().length()+1)*5.5));

        System.out.println(labelUsername.getLayoutX());
    }

    @FXML
    private void onGoBackButtonPressed(){
        setScene(goBackButton.getScene(), ModulFXML.REGISTER, ModuleTitles.REGISTER);
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
