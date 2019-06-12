package sample.Controllers.SceneControllers;

import db.DTO.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.LoginRegister.RegisterInfoController;
import sample.Controllers.SceneControllers.LoginRegister.RegisterVerifyController;
import sample.Resources.ResourceFXML;
import sample.Resources.ResourceTitles;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

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
    protected void setSceneToRegisterVerify(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToRegisterVerify(stage, user);
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

    private void changeSceneToRegisterVerify(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourceFXML.REGISTER_VERIFY_FRAGMENT));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ResourceTitles.REGISTER_VERIFY + user.getUsername());
            stage.setResizable(false);
            stage.show();
            RegisterVerifyController registerVerifyController = loader.getController();
            registerVerifyController.onCreate(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Generating validation code
    String finalcode="";
    protected  String generateValidationCode(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        char[] code = new char[length];

        for(int i = 0; i< length ; i++) {
            code[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        for(int i = 0; i< length ; i++) {
             finalcode=finalcode+code[i];
        }

        return finalcode;
    }


    @Override
    protected void addingNotification(String email) {

    }
}
