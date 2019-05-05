package sample.Controllers.FragmentControllers.DietSceneFragments;


import db.DTO.Diet;
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


    private int recommendedCalories=3000;
    private int recommendedProtein=160;
    private int recommendedWater=30;
    DatabaseModuleDiet databaseModuleDiet = new DatabaseModuleDiet();
    Diet diet= databaseModuleDiet.loadDiet(Session.getUserID());
    public void onCreate() {
        loadWorkoutsIntoLabels();
    }

@FXML
    private void onButtonSaveCaloriesPressed(){
            if(!caloriesTextField.getText().equals("")){
                diet.setActualCalories(diet.getActualCalories()+Integer.parseInt(caloriesTextField.getText()));
                if(databaseModuleDiet.updateCalories(diet.getActualCalories()))

                    System.out.println("Calories collumn updated !");

            }
            loadWorkoutsIntoLabels();

    }
@FXML
    private void onButtonSaveProteinPressed(){
    if(!proteinTextField.getText().equals("")){
        diet.setActualProtein(diet.getActualProtein()+Integer.parseInt(proteinTextField.getText()));
        if(databaseModuleDiet.updateProtein(diet.getActualProtein()))

            System.out.println("Protein collumn updated !");

    }


    loadWorkoutsIntoLabels();

    }
@FXML
    private void onButtonSaveWaterPressed(){
    if(!waterTextField.getText().equals("")){
        diet.setActualWater(diet.getActualWater()+Integer.parseInt(waterTextField.getText()));
        if(databaseModuleDiet.updateWater(diet.getActualWater()))

            System.out.println("Protein collumn updated !");

    }
    loadWorkoutsIntoLabels();

    }

    private void loadWorkoutsIntoLabels() {
        caloriesProgressBar.setProgress((double) diet.getActualCalories()/recommendedCalories);
        proteinProgressBar.setProgress((double) diet.getActualProtein()/recommendedProtein);
        waterProgressBar.setProgress((double) diet.getActualWater()/recommendedWater);
        labelCalories.setText(diet.getActualCalories()+" / "+recommendedCalories+"kcal");
        labelProtein.setText(diet.getActualProtein()+" / "+recommendedProtein+"g");
        labelWater.setText(diet.getActualWater()+" / "+recommendedWater+"dcl");

    }


}
