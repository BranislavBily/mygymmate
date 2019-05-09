package sample.Controllers.PopUpWindowControllers;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.DropShadow;
import sample.Controllers.FeedbackController;

public class ChangePasswordController extends FeedbackController {

    @FXML
    private Button buttonSave;
    @FXML
    private Label labelCurrentPasswordError;
    @FXML
    private Label labelNewPasswordError;
    @FXML
    private Label labelPasswordChanged;
    @FXML
    private PasswordField passwordFieldCurrent;
    @FXML
    private PasswordField passwordFieldNew;
    @FXML
    private PasswordField passwordFieldNewAgain;


    @FXML
    private void onButtonSavePressed() {
        resetAllFeedback();
        String passwordCurrent = passwordFieldCurrent.getText();
        String passwordNew = passwordFieldNew.getText();
        String passwordNewAgain = passwordFieldNewAgain.getText();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        boolean goodInput = true;

        if (passwordCurrent.isEmpty()) {
            labelCurrentPasswordError.setText("Fill this field!");
            labelCurrentPasswordError.setVisible(true);
            System.out.println("Empty current password");
            goodInput = false;
            displayFeedBack(passwordFieldCurrent);
        } else if (!databaseModuleUser.correctUserPassword(passwordCurrent)) {
            labelCurrentPasswordError.setText("Wrong user password!");
            labelCurrentPasswordError.setVisible(true);
            System.out.println("Wrong user password");
            goodInput = false;
            displayFeedBack(passwordFieldCurrent);
        }

        if (passwordNew.isEmpty() || passwordNewAgain.isEmpty()) {
            labelNewPasswordError.setText("Please fill both fields!");
            labelNewPasswordError.setVisible(true);
            System.out.println("Empty new passwords");
            displayFeedBack(passwordFieldNew);
            displayFeedBack(passwordFieldNewAgain);
            goodInput = false;
        } else if (!passwordNew.equals(passwordNewAgain)) {
            labelNewPasswordError.setText("Passwords do not match!");
            labelNewPasswordError.setVisible(true);
            System.out.println("New password do not match!");
            displayFeedBack(passwordFieldNew);
            displayFeedBack(passwordFieldNewAgain);
            goodInput = false;
        } else if (passwordCurrent.equals(passwordNew)) {
            labelNewPasswordError.setText("You need to enter new password!");
            labelNewPasswordError.setVisible(true);
            goodInput = false;
            displayFeedBack(passwordFieldCurrent);
            displayFeedBack(passwordFieldNew);
            displayFeedBack(passwordFieldNewAgain);
            System.out.println("Heslo sa zhoduju");
        }

        if (goodInput) {
            if (!databaseModuleUser.updateUserPassword(passwordNew)) {
                System.out.println("Error while updating user password");
            } else {
                labelPasswordChanged.setVisible(true);
                System.out.println("Heslo uspesne zmenene");
            }
        }
    }

    private void resetAllFeedback() {
        DropShadow dropShadow = getCleanDropShadow();
        passwordFieldCurrent.setEffect(dropShadow);
        passwordFieldNew.setEffect(dropShadow);
        passwordFieldNewAgain.setEffect(dropShadow);
        labelNewPasswordError.setVisible(false);
        labelCurrentPasswordError.setVisible(false);
        labelPasswordChanged.setVisible(false);
    }


}
