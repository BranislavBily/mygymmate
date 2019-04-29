package sample.Controllers.SceneControllers.Trainer;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.HomeSceneController;
import sample.Modules.ModuleFXML;
import sample.Users.Trainer.Trainer;

public class TrainerHomeSceneController extends HomeSceneController {

    private Trainer trainer;
    @FXML
    private Label labelUsername;

    @FXML
    private Button buttonLogOut;
    @FXML
    private Button buttonWorkout;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonMeasure;
    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonSettings;
    @FXML
    private Button buttonTraineeInfo;
    @FXML
    private Button buttonAboutUs;

    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setLabel() {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        String username = databaseModuleUser.getUsername(userID);
        setLabelUsername(labelUsername, username);
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Label getLabelUsername() {
        return labelUsername;
    }

    public void setLabelUsername(Label labelUsername) {
        this.labelUsername = labelUsername;
    }

    @FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene(), userID);
    }

    @FXML
    private void onButtonDietPressed() {
        setSceneToDiet(buttonDiet.getScene(), userID);
    }

    @FXML
    private void onButtonMeasurePressed() {
        setSceneToMeasure(buttonDiet.getScene(), userID);
    }

    @FXML
    private void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

    @FXML
    private void onProfileButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.PROFILE_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onSettingsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.SETTINGS_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onTraineeInfoButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.TRAINEE_INFO_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonTraineeInfo.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTraineeInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }
}

