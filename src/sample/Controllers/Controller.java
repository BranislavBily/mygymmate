package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.Admin.AdminHomeScreenController;
import sample.Controllers.SceneControllers.LoginRegister.RegisterInfoController;
import sample.Controllers.SceneControllers.Trainee.TraineeHomeScreen;
import sample.Controllers.SceneControllers.Trainer.TrainerHomeScreenController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Admin.Admin;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;

//Class that changes scenes
public class Controller extends AnchorPane {
    protected void setScene(Scene scene, String fxml, String title) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, fxml, title);
    }

    private void changeScene(Stage stage, String fxml, String title) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setScene(Scene scene, String fxml, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, fxml, user);

    }

    private void changeScene(Stage stage, String fxml, User user) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            RegisterInfoController registerInfoController = loader.getController();
            registerInfoController.setUser(user);
            stage.setScene(scene);
            stage.setTitle(user.getUsername());
            stage.setResizable(false);
            stage.show();
            registerInfoController.setLabelUsername(registerInfoController.getLabelUsername(), user, 182, 5.5, "Hi ", " !");
            registerInfoController.setChoiceBoxItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Method that changes scenes based on users
    protected void setSceneUser(Scene scene, String fxml, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneUser(stage, fxml, user);

    }
//Method that changes login scene to users scene based on status
    private void changeSceneUser(Stage stage, String fxml, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            if (user instanceof Trainee) {
                TraineeHomeScreen traineeHomeScreen = loader.getController();
                traineeHomeScreen.setTrainee((Trainee) user);
                traineeHomeScreen.setLabelUsername(traineeHomeScreen.getLabelUsername(), user, 162, 5.7, "", "");
            } else if (user instanceof Admin) {
                AdminHomeScreenController adminHomeScreenController = loader.getController();
                adminHomeScreenController.setAdmin((Admin) user);
                adminHomeScreenController.setLabelUsername(adminHomeScreenController.getLabelUsername(), user, 162, 5.7, "", "");
            } else {
                TrainerHomeScreenController trainerHomeScreenController = loader.getController();
                trainerHomeScreenController.setTrainer((Trainer) user);
                trainerHomeScreenController.setLabelUsername(trainerHomeScreenController.getLabelUsername(), user, 162, 5.7, "", "");
            }
            stage.setScene(scene);
            stage.setTitle("MyGymMate ("+user.getUsername()+")");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//Centering and setting Labels based on username.

    public void setLabelUsername(Label labelUsername, User user, int x, double constant, String bonus, String bonus2) {
        labelUsername.setText(bonus + user.getUsername() + bonus2);
        labelUsername.setLayoutX(x - ((user.getUsername().length() + 1) * constant));
    }
    @FXML
    protected void onButtonLogOutPressed(Button buttonLogOut) {
        setScene(buttonLogOut.getScene(), ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

    public Parent Fragment(String FXML){
        Parent fragment = null;
        try {
            fragment = FXMLLoader.load(getClass().getResource(FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public void setButtonsColor(Button buttonProfile, Button buttonSettings, Button buttonTrainerInfo, Button buttonAboutUs){
        buttonProfile.getStyleClass().remove("buttonActive");
        buttonSettings.getStyleClass().remove("buttonActive");
        buttonTrainerInfo.getStyleClass().remove("buttonActive");
        buttonAboutUs.getStyleClass().remove("buttonActive");

    }


}


