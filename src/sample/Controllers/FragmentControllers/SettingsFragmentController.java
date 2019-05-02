package sample.Controllers.FragmentControllers;

import db.DTO.ProfileData;
import db.DatabaseModuleUser;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.Session;

public class SettingsFragmentController {

    private int userID;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldRealName;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxStatus;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonDelete;

    public void onCreate() {
        userID = Session.getUserID();
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        ProfileData profileData = databaseModuleUser.loadUserProfileData(userID);
        textFieldRealName.setText(profileData.getRealName());
        textFieldUsername.setText(profileData.getUsername());
        setChoiceBoxItems();
        choiceBoxGender.setValue(profileData.getGender());
        choiceBoxTypeOfTraining.setValue(profileData.getTypeOfTraining());
        choiceBoxStatus.setValue(profileData.getStatus());
    }

    @FXML
    private void onButtonSavePressed() {
        //Check for user input
        ProfileData profileData = new ProfileData();
        profileData.setRealName(textFieldRealName.getText());
        profileData.setUsername(textFieldUsername.getText());
        profileData.setStatus(choiceBoxStatus.getValue().toString());
        profileData.setGender(choiceBoxGender.getValue().toString());
        profileData.setTypeOfTraining(choiceBoxTypeOfTraining.getValue().toString());
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        databaseModuleUser.updateUser(profileData);
        System.out.println("Settings saved");
        onCreate();
    }

    @FXML
    private void onButtonDeletePressed() {
        //Otvori sa dialogove okno s heslom
        System.out.println("Profile deleted");
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

}
