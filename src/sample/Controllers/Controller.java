package sample.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.Admin.AdminHomeSceneController;
import sample.Controllers.SceneControllers.Trainee.TraineeHomeSceneController;
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

    protected void setSceneToLogin(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
    }

    protected void setSceneToTraineeHomeScene(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTraineeHomeScene(stage, user);
    }

    protected void setSceneToTraineeHomeScene(Scene scene, int userId) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTraineeHomeScene(stage, userId);
    }


    protected void setSceneToTrainerHomeScene(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTrainerHomeScene(stage, user);
    }

    protected void setSceneToTrainerHomeScene(Scene scene, int userID) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToTrainerHomeScene(stage, userID);
    }

    protected void setSceneToAdminHomeScene(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToAdminHomeScene(stage, user);
    }

    protected void setSceneToAdminHomeScene(Scene scene, int userID) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToAdminHomeScene(stage, userID);
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

    private void changeSceneToTraineeHomeScene(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINEE_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.TRAINEE_HOME_SCENE + user.getUsername());
            stage.setResizable(false);
            stage.show();
            TraineeHomeSceneController traineeHomeSceneController = loader.getController();
            traineeHomeSceneController.setTrainee((Trainee) user);
            traineeHomeSceneController.setLabelUsername(traineeHomeSceneController.getLabelUsername(), user, 140, 5.14, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToTraineeHomeScene(Stage stage, int userID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINEE_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            TraineeHomeSceneController traineeHomeSceneController = loader.getController();
            traineeHomeSceneController.setUserID( userID);
            traineeHomeSceneController.setLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToTrainerHomeScene(Stage stage, int userID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINER_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.TRAINER_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            TrainerHomeSceneController trainerHomeSceneController = loader.getController();
            trainerHomeSceneController.setUserID(userID);
            trainerHomeSceneController.setLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToTrainerHomeScene(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.TRAINER_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.TRAINER_HOME_SCENE + user.getUsername());
            stage.setResizable(false);
            stage.show();
            TrainerHomeSceneController trainerHomeSceneController = loader.getController();
            trainerHomeSceneController.setTrainer((Trainer) user);
            trainerHomeSceneController.setLabelUsername(trainerHomeSceneController.getLabelUsername(), user, 140, 5.14, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToAdminHomeScene(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.ADMIN_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.ADMIN_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            AdminHomeSceneController adminHomeSceneController = loader.getController();
            adminHomeSceneController.setAdmin((Admin) user);
            adminHomeSceneController.setLabelUsername(adminHomeSceneController.getLabelUsername(), user, 140, 5.14, "", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeSceneToAdminHomeScene(Stage stage, int userID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.ADMIN_HOME_SCREEN));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.ADMIN_HOME_SCENE);
            stage.setResizable(false);
            stage.show();
            AdminHomeSceneController adminHomeSceneController = loader.getController();
            adminHomeSceneController.setUserID(userID);
            adminHomeSceneController.setLabel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Centering and setting Labels based on username.
    public void setLabelUsername(Label labelUsername, User user, int x, double constant, String bonus, String bonus2) {
        labelUsername.setText(bonus + user.getUsername() + bonus2);
        labelUsername.setLayoutX(x - ((user.getUsername().length() + 1) * constant));
    }

    public void setLabelUsername(Label labelUsername, String username) {
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


