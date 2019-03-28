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
import sample.User;

public class RegisterInfoController extends Controller {

    private User user;

    @FXML
    private Button buttonGoBack;

    @FXML
    private Button buttonFinish;

    @FXML
    private Label labelUsername;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldWeight;

    @FXML
    private TextField textFieldHeight;

    @FXML
    private TextField textFieldDailyIntake;

    @FXML
    private ChoiceBox choiceBoxGender;

    @FXML
    private ChoiceBox choiceBoxTypeOfTraining;

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
    protected void onGoBackButtonPressed(){
        setScene(buttonGoBack.getScene(), ModuleFXML.REGISTER, ModuleTitles.REGISTER);
    }

    @FXML
    protected void onFinishButtonPressed() {
        String firstName = textFieldFirstName.getText();
        String lastName = textFieldLastName.getText();
        String weight = textFieldWeight.getText();
        String height = textFieldHeight.getText();
        String dailyIntake = textFieldDailyIntake.getText();

        //If Names are empty
        if(firstName.equals("")&&lastName.equals("")) {
            System.out.println("Passwords are empty");
        }

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
