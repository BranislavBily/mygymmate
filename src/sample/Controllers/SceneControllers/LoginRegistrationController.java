package sample.Controllers.SceneControllers;

import db.DTO.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.LoginRegister.RegisterInfoController;
import sample.Resources.ResourceFXML;
import sample.Resources.ResourceTitles;

import java.io.IOException;

/**
 * Controller for methods used in Login and Registration Scenes
 */
public class LoginRegistrationController extends Controller {

    protected void setSceneToRegister(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, ResourceFXML.REGISTER, ResourceTitles.REGISTER);
    }

    protected void setSceneToRegisterInfo(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToRegisterInfo(stage, user);
    }

    private void changeSceneToRegisterInfo(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.REGISTER_INFO));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ResourceTitles.REGISTER_INFO + user.getUsername());
            stage.setResizable(false);
            stage.show();
            RegisterInfoController registerInfoController = loader.getController();
            registerInfoController.onCreate(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
