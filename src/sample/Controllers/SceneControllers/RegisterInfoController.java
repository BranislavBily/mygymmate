package sample.Controllers.SceneControllers;

import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Modules.TypeOfTraining;
import sample.Users.Trainee.Trainee;
import sample.Users.User;
import sample.Users.UserInfo;

public class RegisterInfoController extends Controller {

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
    private TextField textFieldDailyCalories;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    @FXML
    private Button buttonGoBack;

    @FXML
    private Label labelUsername;


    public void setUser(User user) {
        this.user = user;
    }

    public void setLabelUsername() {

        labelUsername.setText("Hi " + user.getUsername() + " !");
        labelUsername.setLayoutX(182 - ((user.getUsername().length() + 1) * 5.5));
    }

    @FXML
    private void onButtonGoBackPressed() {
        setScene(buttonGoBack.getScene(), ModuleFXML.REGISTER, ModuleTitles.REGISTER);
    }

    @FXML
    private void onButtonFinishPressed() {
        boolean errorRegistering = false;
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String weight = textFieldWeight.getText();
        String height = textFieldHeight.getText();
        String dailyIntake = textFieldDailyCalories.getText();

        //If Names are empty
        if (firstName.equals("") && lastName.equals("")) {
            //display feedback
            errorRegistering = true;
            System.out.println("Passwords are empty");
        }
        if (weight.equals("") || height.equals("")) {
            errorRegistering = true;
            System.out.println("Empty Weight or Height");
        }
        if (dailyIntake.equals("")) {
            errorRegistering = true;
            System.out.println("Empty Daily intake");
        }
        if (choiceBoxGender == null) {
            errorRegistering = true;
            System.out.println("Empty box gender");
        }
        if (choiceBoxTypeOfTraining == null) {
            errorRegistering = true;
            System.out.println("Empty box type of training");
        }
        if (!errorRegistering) {
            Trainee trainee = new Trainee(user.getUsername(), user.getPassword());
            //Creates new UserInfo, sets values from user input
            UserInfo userInfoTrainee = new UserInfo(choiceBoxGender.getValue().toString(),firstName, lastName, Double.parseDouble(weight),
                    Double.parseDouble(height),Double.parseDouble(dailyIntake), TypeOfTraining.valueOf(choiceBoxTypeOfTraining.getValue().toString()));
            trainee.setUserInfo(userInfoTrainee);
            System.out.println(trainee.toString());
            DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();

            if(databaseModuleUser.insertTraineeInfoToDatabase(trainee)) {
                setScene(textFieldDailyCalories.getScene(), ModuleFXML.LOGIN, ModuleTitles.LOG_IN);
            } else {
                System.out.println("Error while inserting user");
            }
        }

    }


    public void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "male", "female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose_Weight", "Gain_Muscle")
        );
    }
}
