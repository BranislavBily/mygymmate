package sample.Controllers.SceneControllers.LoginRegister;

import db.DTO.RegisteredUser;
import db.DTO.User;
import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import sample.Controllers.SceneControllers.LoginRegistrationController;

public class RegisterInfoController extends LoginRegistrationController {

    private User user;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldWeight;

    @FXML
    private TextField textFieldHeight;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    @FXML
    private ChoiceBox choiceBoxStatus;

    @FXML
    private Button buttonGoBack;

    @FXML
    private Label labelUsername;

    @FXML
    private Label mandatoryError;

    /**
     * Prepares scene for use
     * @param user data from previous scene
     */
    public void onCreate(User user) {
        this.user = user;
        setChoiceBoxItems();
        setLabelUsernameInRegisterInfo(user.getUsername());
    }

    @FXML
    private void onButtonFinishPressed() {
        resetAllFeedback();
        boolean errorRegistering = false;
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String weight = textFieldWeight.getText();
        String height = textFieldHeight.getText();

        if (firstName.equals("")) {
            displayFeedBack(textFieldFirstName);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("Name is empty");
        }
        if (lastName.equals("")) {
            displayFeedBack(textFieldLastName);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("LastName is empty");
        }
        if (weight.equals("") || !isDouble(weight) || impossibleWeight(weight)) {
            displayFeedBack(textFieldWeight);
            System.out.println("Empty Weight");
            mandatoryError.setVisible(true);
            errorRegistering = true;
        } else if (impossibleWeight(weight)) {
            //TODO Vlastny error an rozhas vahy
        }
        if (height.equals("") || !isDouble(height)) {
            displayFeedBack(textFieldHeight);
            System.out.println("Empty Height");
            mandatoryError.setVisible(true);
            errorRegistering = true;
        } else if (impossibleHeight(height)) {
            //TODO Vlastny error na rozsah vysky
        }
        if (choiceBoxStatus.getValue() == null) {
            displayErrorFeedbackChoiceBox(choiceBoxStatus);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("Empty box status");
        }
        if (choiceBoxGender.getValue() == null) {
            displayErrorFeedbackChoiceBox(choiceBoxGender);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("Empty box gender");
        }
        if (choiceBoxTypeOfTraining.getValue() == null) {
            displayErrorFeedbackChoiceBox(choiceBoxTypeOfTraining);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("Empty box type of training");
        }
        if (!errorRegistering) {
            RegisteredUser registeredUser = new RegisteredUser(user.getUsername(), user.getPassword(), choiceBoxStatus.getValue().toString(),
                    textFieldFirstName.getText(), textFieldLastName.getText(), Double.parseDouble(textFieldWeight.getText()),
                    Double.parseDouble(textFieldHeight.getText()), choiceBoxGender.getValue().toString(), choiceBoxTypeOfTraining.getValue().toString().replace("_", " "));
            DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
            if(databaseModuleUser.insertUserIntoDatabase(registeredUser)) {
                if(databaseModuleUser.insertUserDietIntoDatabase(databaseModuleUser.isUser(user.getUsername(),user.getPassword()))){
                setSceneToLogin(buttonGoBack.getScene());}

            } else {
                System.out.println("Error while inserting");
            }
        }
    }

    private void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "Male", "Female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose Weight", "Gain Muscle")
        );

        choiceBoxStatus.setItems(FXCollections.observableArrayList(
                "Trainee", "Trainer")
        );
    }

    private void resetAllFeedback() {
        DropShadow dropShadow = getCleanDropShadow();
        mandatoryError.setVisible(false);
        textFieldFirstName.setEffect(dropShadow);
        textFieldLastName.setEffect(dropShadow);
        textFieldHeight.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        choiceBoxStatus.setEffect(dropShadow);
        choiceBoxTypeOfTraining.setEffect(dropShadow);
        choiceBoxGender.setEffect(dropShadow);
    }

    private boolean impossibleWeight(String sWeight) {
        double weight = Double.parseDouble(sWeight);
        return weight < 40 || weight > 250;
    }

    private boolean impossibleHeight(String sHeight) {
        double height = Double.parseDouble(sHeight);
        return height < 100 || height > 250;
    }

    //Centering and setting Labels based on username.
    private void setLabelUsernameInRegisterInfo(String username) {
        labelUsername.setText("Hi " + username + "!");
        labelUsername.setLayoutX(182 - ((username.length() + 1) * 5.5));
    }
    @FXML
    private void onButtonGoBackPressed() {
        setSceneToRegister(buttonGoBack.getScene());
    }

}
