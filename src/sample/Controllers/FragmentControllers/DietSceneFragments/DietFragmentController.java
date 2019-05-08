package sample.Controllers.FragmentControllers.DietSceneFragments;


import db.DTO.Diet;
import db.DTO.UserDietInfo;
import db.DTO.Workout;
import db.DatabaseModuleDiet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Session;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DietFragmentController {

    @FXML
    private ProgressBar caloriesProgressBar;
    @FXML
    private ProgressBar proteinProgressBar;
    @FXML
    private ProgressBar waterProgressBar;
    @FXML
    private TextField caloriesTextField;
    @FXML
    private TextField proteinTextField;
    @FXML
    private TextField waterTextField;
    @FXML
    private Button buttonSaveCalories;
    @FXML
    private Button buttonSaveProtein;
    @FXML
    private Button buttonSaveWater;
    @FXML
    private Label labelCalories;
    @FXML
    private Label labelProtein;
    @FXML
    private Label labelWater;


    private int recommendedCalories = 3000;
    private int recommendedProtein = 160;
    private int recommendedWater = 30;

    DatabaseModuleDiet databaseModuleDiet = new DatabaseModuleDiet();
    Diet diet = databaseModuleDiet.loadDiet(Session.getUserID());

    public void onCreate() {

        if (!diet.getDate().equals(new SimpleDateFormat("dd.MM.yyyy").format(new Date()))) {
            nullDiet();
            System.out.println("new day");

        }
        setRecommendedNutrition();
        loadDietIntoLabels();

    }

    @FXML
    private void onButtonSaveCaloriesPressed() {


        if (!caloriesTextField.getText().equals("") && numberOrNot(caloriesTextField.getText())) {
            diet.setActualCalories(diet.getActualCalories() + Integer.parseInt(caloriesTextField.getText()));
            if (databaseModuleDiet.updateCalories(diet.getActualCalories())) {
                caloriesTextField.setText("");
                System.out.println("Calories collumn updated !");
            }
        }


        loadDietIntoLabels();

    }

    @FXML
    private void onButtonSaveProteinPressed() {
        if (!proteinTextField.getText().equals("") && numberOrNot(proteinTextField.getText())) {
            diet.setActualProtein(diet.getActualProtein() + Integer.parseInt(proteinTextField.getText()));
            if (databaseModuleDiet.updateProtein(diet.getActualProtein())) {
                proteinTextField.setText("");
                System.out.println("Protein collumn updated !");
            }
        }


        loadDietIntoLabels();


    }

    @FXML
    private void onButtonSaveWaterPressed() {
        if (!waterTextField.getText().equals("") && numberOrNot(waterTextField.getText())) {
            diet.setActualWater(diet.getActualWater() + Integer.parseInt(waterTextField.getText()));
            if (databaseModuleDiet.updateWater(diet.getActualWater())) {
                waterTextField.setText("");
                System.out.println("Protein collumn updated !");
            }
        }

        loadDietIntoLabels();


    }

    private void loadDietIntoLabels() {
        caloriesProgressBar.setProgress((double) diet.getActualCalories() / recommendedCalories);
        proteinProgressBar.setProgress((double) diet.getActualProtein() / recommendedProtein);
        waterProgressBar.setProgress((double) diet.getActualWater() / recommendedWater);



        if (diet.getActualProtein() >= recommendedProtein) {
            labelProtein.setText("Done!");
            buttonSaveProtein.setDisable(true);
        }else {
            labelProtein.setText(diet.getActualProtein() + " / " + recommendedProtein + "g");
            buttonSaveProtein.setDisable(false);
        }
        if (diet.getActualCalories() >= recommendedCalories) {
            labelCalories.setText("Done!");
            buttonSaveCalories.setDisable(true);
        }else {
            labelCalories.setText(diet.getActualCalories() + " / " + recommendedCalories + "kcal");
            buttonSaveCalories.setDisable(false);
        }
        if (diet.getActualWater() >= recommendedWater) {
            labelWater.setText("Done!");
            buttonSaveWater.setDisable(true);
        }else {

            labelWater.setText(diet.getActualWater() + " / " + recommendedWater + "dcl");
            buttonSaveWater.setDisable(false);
        }

    }

    private void nullDiet() {
        diet.setActualWater(0);
        diet.setActualProtein(0);
        diet.setActualCalories(0);

        databaseModuleDiet.updateCalories(0);
        databaseModuleDiet.updateProtein(0);
        databaseModuleDiet.updateWater(0);
        databaseModuleDiet.upadteDate(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
    }

    private boolean numberOrNot(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }


    private void setRecommendedNutrition() {
        UserDietInfo userDietInfo = databaseModuleDiet.getUserDietInfo(Session.getUserID());
        userDietInfo.toString();

        recommendedCalories = getRecommendedCalories(userDietInfo.getWeight(), userDietInfo.getHeight(), userDietInfo.getGender(), userDietInfo.getTypeOfTraining());
        recommendedProtein = getRecommendedProtein(userDietInfo.getWeight(), userDietInfo.getGender());
        recommendedWater = getRecommendedWater(userDietInfo.getWeight());
    }


    private int getRecommendedCalories(double weight, double height, String gender, String typeOfTraining) {
        int recommendedCalories = 0;
        if (gender.equals("Male")) {
            if (typeOfTraining.equals("Gain Muscle")) {
                recommendedCalories = (int) ((int) (10 * weight + 6.25 * height - 120) * 1.65 + 500);
            } else if (typeOfTraining.equals("Lose Weight")) {
                recommendedCalories = (int) ((int) (10 * weight + 6.25 * height - 120) * 1.65 - 500);
            }

        } else if (gender.equals("Female")) {
            if (typeOfTraining.equals("Gain Muscle")) {
                recommendedCalories = (int) ((int) (10 * weight + 6.25 * height - 300) * 1.65 + 250);
            } else if (typeOfTraining.equals("Lose Weight")) {
                recommendedCalories = (int) ((int) (10 * weight + 6.25 * height - 300) * 1.65 - 250);
            }

        }
        return recommendedCalories;
    }

    private int getRecommendedProtein(double weight, String gender) {
        int recommendedProtein = 0;
        if (gender.equals("Male")) {
            recommendedProtein = (int) ((int) weight * 1.8);
        } else if (gender.equals("Female")) {
            recommendedProtein = (int) ((int) weight * 1.5);
        }
        return recommendedProtein;
    }

    private int getRecommendedWater(double weight) {
        return (int) Math.round((((weight * 2.2) / 3 * 2) / 33.96 + 1) * 10);
    }


}
