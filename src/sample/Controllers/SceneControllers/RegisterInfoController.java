package sample.Controllers.SceneControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Controllers.Controller;
import sample.Modules.ModuleFXML;
import sample.Modules.ModuleTitles;
import sample.Users.Trainee.Trainee;
import sample.Users.User;

public class RegisterInfoController extends Controller {

    private User user;

    @FXML
    private Button buttonGoBack;

    @FXML
    private Button buttonFinish;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldWeight;

    @FXML
    private TextField textFieldHeight;

    @FXML
    private TextField textFieldDailyCalories;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

    @FXML
    private Label labelUsername;


    public void setUser(User user) {
        this.user = user;
        System.out.println(user.toString());
    }

    public void setLabelUsername() {
        labelUsername.setText("Hi "+user.getUsername() + " !");
        labelUsername.setLayoutX(182-((user.getUsername().length()+1)*5.5));

        choiceBoxGender.getValue();
    }

    @FXML
    public void onGoBackButtonPressed(){
        setScene(buttonGoBack.getScene(), ModuleFXML.REGISTER, ModuleTitles.REGISTER);
    }


    @FXML
    public void onFinishButtonPressed() {
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String weight = textFieldWeight.getText();
        String height = textFieldHeight.getText();
        String dailyIntake = textFieldDailyCalories.getText();

        //If Names are empty
        if(firstName.equals("") && lastName.equals("")) {
            //display feedback
            System.out.println("Passwords are empty");
        }
        if(weight.equals("") || height.equals("")) {
            System.out.println("Empty Weight or Height");
        }
        if(dailyIntake.equals("")) {
            System.out.println("Empty Daily intake");
        }
        if(choiceBoxGender == null) {
            System.out.println("Empty box gender");
        }
        if(choiceBoxTypeOfTraining == null) {
            System.out.println("Empty box type of training");
        }
//        Trainee trainee = createTraineeFromInput();
    }

//    private Trainee createTraineeFromInput() {
//        double weight = Double.parseDouble()
//        return new Trainee(user.getUsername(), user.getPassword(), choiceBoxGender.getValue().toString(),
//                    );
//    }

    public void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "male", "female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose weigh", "Gain muscle")
        );
    }
}
