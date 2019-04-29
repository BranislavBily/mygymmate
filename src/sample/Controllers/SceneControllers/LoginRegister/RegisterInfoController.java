package sample.Controllers.SceneControllers.LoginRegister;

import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import sample.Controllers.LoginRegistrationController;
import sample.Modules.TypeOfTraining;
import sample.Users.Trainee.Trainee;
import sample.Users.Trainer.Trainer;
import sample.Users.User;
import sample.Users.UserInfo;

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


    public void setUser(User user) {
        this.user = user;
        System.out.println(this.user.toString());
    }

    public Label getLabelUsername() {
        return labelUsername;
    }

    public void setLabelUsername(Label labelUsername) {
        this.labelUsername = labelUsername;
    }

    @FXML
    private void onButtonGoBackPressed() {
        setSceneToRegister(buttonGoBack.getScene());
    }

    @FXML
    private void onButtonFinishPressed() {
        resetAllFeedback();
        boolean errorRegistering = false;
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String weight = textFieldWeight.getText();
        String height = textFieldHeight.getText();


        //If Names are empty
        if (firstName.equals("")) {
            displayErrorFeedbackUsername(textFieldFirstName);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("Name is empty");
        }
        if (lastName.equals("")) {
            displayErrorFeedbackUsername(textFieldLastName);
            errorRegistering = true;
            mandatoryError.setVisible(true);
            System.out.println("LastName is empty");
        }
        if (weight.equals("") || !numberOrNot(weight) || !possibleWeight(weight)) {
            displayErrorFeedbackUsername(textFieldWeight);
            System.out.println("Empty Weight");
            mandatoryError.setVisible(true);
            errorRegistering = true;
        }
        if (height.equals("") || !numberOrNot(height) || !possibleHeight(height)) {
            displayErrorFeedbackUsername(textFieldHeight);
            System.out.println("Empty Height");
            mandatoryError.setVisible(true);
            errorRegistering = true;
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
            //Creates new UserInfo, sets values from user input, TODO for the love of god rework this please
            UserInfo userInfo = new UserInfo(choiceBoxGender.getValue().toString(), firstName, lastName, Double.parseDouble(weight),
                    Double.parseDouble(height), choiceBoxStatus.getValue().toString(), TypeOfTraining.valueOf(choiceBoxTypeOfTraining.getValue().toString()));
            if (userInfo.getStatus().equals("Trainee")) {
                Trainee trainee = new Trainee(user.getUsername(), user.getPassword());
                trainee.setUserInfo(userInfo);
                System.out.println(trainee.toString());
                DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
                if (databaseModuleUser.insertTraineeInfoToDatabase(trainee)) {
                    setSceneToLogin(textFieldFirstName.getScene());
                } else {
                    System.out.println("Error while inserting Trainee");
                }

            } else if (userInfo.getStatus().equals("Trainer")) {
                Trainer trainer = new Trainer(user.getUsername(), user.getPassword());
                trainer.setUserInfo(userInfo);
                System.out.println(trainer.toString());
                DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
                if (databaseModuleUser.insertTrainerInfoToDatabase(trainer)) {
                    setSceneToLogin(textFieldFirstName.getScene());
                } else {
                    System.out.println("Error while inserting Trainer");
                }
            }
        }
    }

    public void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "Male", "Female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose_Weight", "Gain_Muscle")
        );

        choiceBoxStatus.setItems(FXCollections.observableArrayList(
                "Trainee", "Trainer")
        );
    }

    private void resetAllFeedback() {
        mandatoryError.setVisible(false);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setColor(Color.color(0, 0, 0));
        textFieldFirstName.setEffect(dropShadow);
        textFieldLastName.setEffect(dropShadow);
        textFieldHeight.setEffect(dropShadow);
        textFieldWeight.setEffect(dropShadow);
        choiceBoxStatus.setEffect(dropShadow);
        choiceBoxTypeOfTraining.setEffect(dropShadow);
        choiceBoxGender.setEffect(dropShadow);
    }

    private boolean numberOrNot(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private boolean possibleWeight(String sWeight) {
        double weight = Double.parseDouble(sWeight);
        return weight < 40 || weight > 250;
    }

    private boolean possibleHeight(String sHeight) {
        double height = Double.parseDouble(sHeight);
        return height < 100 || height > 250;
    }

    //Centering and setting Labels based on username.
    public void setLabelUsernameInRegisterInfo(Label labelUsername, String username) {
        labelUsername.setText("Hi " + username + "!");
        labelUsername.setLayoutX(182 - ((username.length() + 1) * 5.5));
    }

}
