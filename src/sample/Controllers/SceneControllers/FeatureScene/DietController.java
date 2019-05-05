package sample.Controllers.SceneControllers.FeatureScene;

import db.DatabaseModuleUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Controllers.FragmentControllers.DietSceneFragments.BMIFragmentController;
import sample.Controllers.FragmentControllers.DietSceneFragments.DietFragmentController;
import sample.Controllers.FragmentControllers.DietSceneFragments.WeightFragmentController;
import sample.Controllers.SceneControllers.HomeSceneController;
import sample.Resources.ResourceFXML;
import sample.Resources.ResourceUserType;

import java.io.IOException;

public class DietController extends HomeSceneController {

    @FXML
    private AnchorPane mainFragment;
    @FXML
    private Button buttonDiet;
    @FXML
    private Button buttonWeight;
    @FXML
    private Button buttonBMI;
    @FXML
    private Label labelUsername;

    private DatabaseModuleUser databaseModuleUser;

    public void onCreate() {
        databaseModuleUser = new DatabaseModuleUser();
        setLabel();
        onButtonDietPressed();
    }

    private void setLabel() {
        String username = databaseModuleUser.getUsername();
        setLabelUsername(labelUsername, username);
    }

    @FXML
    private void onGoBackImagePressed() {
        String status = databaseModuleUser.getUserStatus();
        if(status.equals(ResourceUserType.TRAINEE)) {
            setSceneToTraineeHomeScene(buttonDiet.getScene());
        } else {
            setSceneToTrainerHomeScene(buttonDiet.getScene());
        }
    }

    @FXML
    private void onButtonDietPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.DIET_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            DietFragmentController dietFragmentController = fxmlLoader.getController();
            dietFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonDiet ,buttonBMI, buttonWeight);
            buttonDiet.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonWeightPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.WEIGHT_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            WeightFragmentController weightFragmentController = fxmlLoader.getController();
            weightFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonDiet ,buttonBMI, buttonWeight);
            buttonWeight.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onButtonBMIPressed() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ResourceFXML.BMI_FRAGMENT));
        try {
            Parent fragment = fxmlLoader.load();
            BMIFragmentController bmiFragmentController = fxmlLoader.getController();
            bmiFragmentController.onCreate();
            mainFragment.getChildren().setAll(fragment);
            removeButtonActiveEffect(buttonDiet ,buttonBMI, buttonWeight);
            buttonBMI.getStyleClass().add("buttonActive");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("BMI");
    }



}
