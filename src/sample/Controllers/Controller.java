package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.Admin.AdminHomeSceneController;
import sample.Controllers.SceneControllers.Trainee.TraineeHomeScene;
import sample.Controllers.SceneControllers.Trainer.TrainerHomeSceneController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Admin.Admin;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;
import java.io.IOException;

//Controller for methods that can be used in all Controllers
public class Controller extends AnchorPane {

    protected void setScene(Scene scene, String fxml, String title) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, fxml, title);
    }

    protected void setSceneToLogin(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

    //This method is called when sending User is not necessary
    protected void changeScene(Stage stage, String fxml, String title) {
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

    //Method that changes scenes based on users
    protected void setSceneUser(Scene scene, String fxml, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneUser(stage, fxml, user);

    }

    protected void setSceneToTraineeHomeScene(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTraineeHomeScene(stage, user);
    }

    private void changeSceneToTraineeHomeScene(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINEE_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.USER_HOME_SCREEN);
            stage.setResizable(false);
            stage.show();
            TraineeHomeScene traineeHomeScene = loader.getController();
            traineeHomeScene.setTrainee((Trainee) user);
            traineeHomeScene.setLabelUsername(traineeHomeScene.getLabelUsername(), user, 140, 5, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method that changes login scene to users scene based on status
    private void changeSceneUser(Stage stage, String fxml, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            if (user instanceof Trainee) {
                TraineeHomeScene traineeHomeScene = loader.getController();
                traineeHomeScene.setTrainee((Trainee) user);
                traineeHomeScene.setLabelUsername(traineeHomeScene.getLabelUsername(), user, 140, 5, "", "");
            } else if (user instanceof Admin) {
                AdminHomeSceneController adminHomeSceneController = loader.getController();
                adminHomeSceneController.setAdmin((Admin) user);
                adminHomeSceneController.setLabelUsername(adminHomeSceneController.getLabelUsername(), user, 162, 5.7, "", "");
            } else {
                TrainerHomeSceneController trainerHomeSceneController = loader.getController();
                trainerHomeSceneController.setTrainer((Trainer) user);
                trainerHomeSceneController.setLabelUsername(trainerHomeSceneController.getLabelUsername(), user, 162, 5.7, "", "");
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

    protected Parent loadFragmentFromFXML(String FXML){
        Parent fragment = null;
        try {
            fragment = FXMLLoader.load(getClass().getResource(FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    protected void removeButtonActiveEffect(Button buttonProfile, Button buttonSettings, Button buttonTrainerInfo, Button buttonAboutUs){
        buttonProfile.getStyleClass().remove("buttonActive");
        buttonSettings.getStyleClass().remove("buttonActive");
        buttonTrainerInfo.getStyleClass().remove("buttonActive");
        buttonAboutUs.getStyleClass().remove("buttonActive");
    }
}


