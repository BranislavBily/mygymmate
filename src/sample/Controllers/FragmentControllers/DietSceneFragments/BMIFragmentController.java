package sample.Controllers.FragmentControllers.DietSceneFragments;

import db.DTO.UserDietInfo;
import db.DatabaseModuleDiet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Session;

import javax.swing.text.html.ImageView;

public class BMIFragmentController {


    @FXML
    private Label labelBMI;
    @FXML
    private Label labelArrow;
    @FXML
    private Button buttonManual;
    @FXML
    private Button buttonYour;



    DatabaseModuleDiet databaseModuleDiet=new DatabaseModuleDiet();
    UserDietInfo userDietInfo=new UserDietInfo();
    public void onCreate() {
        userDietInfo=databaseModuleDiet.getUserDietInfo();
        labelBMI.setText(""+ Math.round( getMyBMI(userDietInfo.getWeight(),userDietInfo.getHeight())* 10.0) / 10.0);
        setArrow(Math.round( getMyBMI(userDietInfo.getWeight(),userDietInfo.getHeight())* 10.0) / 10.0);
    }



    private double getMyBMI(double weight, double height){

        return weight/((height/100)*(height/100));
    }

    private void setArrow(double bmi){


        labelArrow.setLayoutX((int)bmi*30.5-447);


    }

    private void onButtonManualPressed(){
        onCreate();

    }

}
