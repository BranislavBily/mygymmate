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
import sample.Users.User;

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
        System.out.println(user.toString());
    }

    public void setLabelUsername() {

        labelUsername.setText("Hi "+user.getUsername() + " !");
        labelUsername.setLayoutX(182-((user.getUsername().length()+1)*5.5));

        System.out.println(labelUsername.getLayoutX());
        choiceBoxGender.getValue();
    }

    @FXML
    private void onButtonGoBackPressed(){
        setScene(buttonGoBack.getScene(), ModuleFXML.REGISTER, ModuleTitles.REGISTER);
    }

    @FXML
    private void onButtonFinishPressed() {
        checkInputs();
    }

    private void checkInputs() {

    }



    public void setChoiceBoxItems() {
        choiceBoxGender.setItems(FXCollections.observableArrayList(
                "male", "female")
        );

        choiceBoxTypeOfTraining.setItems(FXCollections.observableArrayList(
                "Lose weigh", "Gain muscle")
        );
    }
}
