package sample.Controllers.SceneControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.LoginRegister.RegisterInfoController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import db.DTO.User;

import java.io.IOException;

/**
 * Controller for methods used in Login and Registration Scenes
 */
public class LoginRegistrationController extends Controller {

    protected void displayErrorFeedbackUsername(TextField textField){
        textField.setText("");
        DropShadow dropShadowUsername = new DropShadow();
       dropShadowUsername.setRadius(20);
       dropShadowUsername.setColor(Color.color(1,0,0));
        textField.setEffect(dropShadowUsername);
    }

    protected void displayErrorFeedbackPassword(PasswordField passwordField){
        passwordField.setText("");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setColor(Color.color(1,0,0));
        passwordField.setEffect(dropShadow);
    }
    protected void displayErrorFeedbackChoiceBox(ChoiceBox choiceBox){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(20);
        dropShadow.setColor(Color.color(1,0,0));
        choiceBox.setEffect(dropShadow);
    }

    protected void setSceneToRegister(Scene scene) {
        Stage stage = (Stage) scene.getWindow();
        changeScene(stage, ModuleFXML.REGISTER, ModuleTitles.REGISTER);
    }

    protected void setSceneToRegisterInfo(Scene scene, User user) {
        Stage stage = (Stage) scene.getWindow();
        changeSceneToRegisterInfo(stage, user);
    }

    private void changeSceneToRegisterInfo(Stage stage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ModuleFXML.REGISTER_INFO));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(ModuleTitles.REGISTER_INFO + user.getUsername());
            stage.setResizable(false);
            stage.show();
            RegisterInfoController registerInfoController = loader.getController();
            registerInfoController.setUser(user);
            registerInfoController.setLabelUsernameInRegisterInfo(registerInfoController.getLabelUsername(), user.getUsername());
            registerInfoController.setChoiceBoxItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
