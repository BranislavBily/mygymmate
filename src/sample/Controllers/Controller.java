package sample.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.Admin.AdminHomeSceneController;
import sample.Controllers.SceneControllers.Trainee.TraineeHomeSceneController;
import sample.Controllers.SceneControllers.Trainer.TrainerHomeSceneController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;

import java.io.IOException;

/**
 * Controller for methods used in all scenes
 */
public class Controller extends AnchorPane {

    protected void setSceneToLogin(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

    protected void setSceneToTraineeHomeScene(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTraineeHomeScene(stage);
    }

    protected void setSceneToTrainerHomeScene(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTrainerHomeScene(stage);
    }

    protected void setSceneToAdminHomeScene(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToAdminHomeScene(stage);
    }

    //This method is called when sending User is not necessary
    void changeScene(Stage stage, String fxml, String title) {
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

    private void changeSceneToTraineeHomeScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINEE_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(ModuleTitles.TRAINEE_HOME_SCENE);
            stage.show();
            TraineeHomeSceneController traineeHomeSceneController = loader.getController();
            traineeHomeSceneController.onCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToTrainerHomeScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINER_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.TRAINER_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            TrainerHomeSceneController trainerHomeSceneController = loader.getController();
            trainerHomeSceneController.onCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToAdminHomeScene(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.ADMIN_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.ADMIN_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            AdminHomeSceneController adminHomeSceneController = loader.getController();
            adminHomeSceneController.onCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setLabelUsername(Label labelUsername, String username) {
        labelUsername.setText(username);
        labelUsername.setLayoutX(140 - ((username.length() + 1) * 5.14));
    }


    protected void removeButtonActiveEffect(Button buttonProfile, Button buttonSettings, Button buttonTrainerInfo, Button buttonAboutUs){
        buttonProfile.getStyleClass().remove("buttonActive");
        buttonSettings.getStyleClass().remove("buttonActive");
        buttonTrainerInfo.getStyleClass().remove("buttonActive");
        buttonAboutUs.getStyleClass().remove("buttonActive");
    }
    protected void removeAdminButtonActiveEffect(Button buttonProfile, Button buttonSettings, Button buttonAboutUs){
        buttonProfile.getStyleClass().remove("buttonActive");
        buttonSettings.getStyleClass().remove("buttonActive");
        buttonAboutUs.getStyleClass().remove("buttonActive");
    }

    @FXML
    private void onHyperLinkPaypalPressed() {
        Application application = new Application() {
            @Override
            public void start(Stage primaryStage) {
            }
        };
        application.getHostServices().showDocument("https://paypal.me/pools/c/8dO80GARuK");
    }
}


