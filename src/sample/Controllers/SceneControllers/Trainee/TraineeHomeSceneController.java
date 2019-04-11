package sample.Controllers.SceneControllers.Trainee;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import sample.Controllers.HomeSceneController;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;


public class TraineeHomeSceneController extends HomeSceneController {

    private Trainee trainee;
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
    private Button buttonTrainerInfo;
    @FXML
    private Button buttonAboutUs;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelQuote;



    public Label getLabelUsername() {
        return labelUsername;
    }

    public void setLabelUsername(Label labelUsername) {
        this.labelUsername = labelUsername;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
        //this.trainee = loadTrainee(trainee.getId());
    }

    @FXML
    public void onButtonLogOutPressed() {
        super.onButtonLogOutPressed(buttonLogOut);
    }

    @FXML
    private void onButtonWorkoutPressed() {
        setSceneToWorkout(buttonWorkout.getScene(), trainee);
    }

    @FXML
    private void onButtonMeasurePressed() {
        setScene(buttonMeasure.getScene(), ModuleFXML.MEASURE_HOME_SCREEN, ModuleTitles.USER_HOME_SCENE);
    }

    @FXML
    private void onButtonDietPressed() {
        setScene(buttonDiet.getScene(), ModuleFXML.DIET_HOME_SCREEN, ModuleTitles.USER_HOME_SCENE);
    }

    @FXML
    private void onProfileButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.PROFILE_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonProfile.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onSettingsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.SETTINGS_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonSettings.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onTrainerInfoButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.TRAINER_INFO_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonTrainerInfo.getStyleClass().add("buttonActive");
    }
    @FXML
    private void onAboutUsButtonClicked() {
        Parent fragment= loadFragmentFromFXML(ModuleFXML.ABOUT_US_FRAGMENT);
        mainFragment.getChildren().setAll(fragment);
        removeButtonActiveEffect(buttonProfile, buttonSettings, buttonTrainerInfo, buttonAboutUs);
        buttonAboutUs.getStyleClass().add("buttonActive");
    }

}



