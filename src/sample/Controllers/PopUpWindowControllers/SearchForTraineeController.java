package sample.Controllers.PopUpWindowControllers;

import db.DTO.ProfileData;
import db.DTO.User;
import db.DatabaseModuleInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Controllers.SceneControllers.Controller;
import sample.Controllers.SceneControllers.LoginRegister.LoginController;
import sample.Controllers.SceneControllers.LoginRegister.RegisterController;
import sample.Controllers.SceneControllers.LoginRegistrationController;
import sample.Dialogs.AddTraineeDialog;

import java.util.ArrayList;
import java.util.Optional;

public class SearchForTraineeController extends Controller {

    private String TrainerEmail=LoginController.LoggedUserEmail;
    private String TrainerUsername = LoginController.LoggedUserName;


    @FXML
    private TableView<ProfileData> tableViewTrainees;
    @FXML
    private TableColumn<ProfileData, String> tableColumnUsername;
    @FXML
    private TableColumn<ProfileData, String> tableColumnFirstName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnLastName;
    @FXML
    private TableColumn<ProfileData, String> tableColumnTypeOfTraining;
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelSuccess;



    private DatabaseModuleInfo databaseModuleInfo;

    public void onCreate() {
        databaseModuleInfo = new DatabaseModuleInfo();
        setTableViewOnDoubleClickListener();
        setTextFieldNameOnChangeListener();

    }



    private void setTableViewOnDoubleClickListener() {
        tableViewTrainees.setPlaceholder(new Label("No trainees found!"));
        tableViewTrainees.setRowFactory(table -> {
            TableRow<ProfileData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ProfileData profileData = row.getItem();
                    boolean deleteConfirmed = getAddTraineeDialogAnswer(profileData.getRealName());
                    if(deleteConfirmed) {
                        if(databaseModuleInfo.addTraineeToTrainer(profileData.getId())) {
                            labelSuccess.setVisible(true);
                            System.out.println("Trainee added");
                            //If trainee added, clears table
                            textFieldName.setText("");
                            String email = databaseModuleInfo.getTraineeEmailFromDbByUsername(profileData.getUsername());
                            addingNotification(email);

                        } else {
                            System.out.println("Error occurred when adding user");
                        }
                    } else {
                        //Do nothing
                        System.out.println("Nothing");
                    }
                }
            });
            return row;
        });
    }

    private void setTextFieldNameOnChangeListener() {
        textFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()) {
                tableViewTrainees.getItems().clear();
            } else {
                labelSuccess.setVisible(false);
                String[] name = newValue.split(" ");
                ArrayList<ProfileData> trainees;
                //If user entered first and last name
                try {
                    trainees = databaseModuleInfo.getAllTraineesByName(name[0].concat("%"), name[1].concat("%"));
                } catch (ArrayIndexOutOfBoundsException e) {
                    //If he only entered first name
                    trainees = databaseModuleInfo.getAllTraineesByName(name[0].concat("%"));
                }
                loadTraineesIntoTable(trainees);
            }
        });
    }

    private void loadTraineesIntoTable(ArrayList<ProfileData> trainees) {
        tableColumnUsername.setCellValueFactory(new PropertyValueFactory("Username"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory("FirstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory("LastName"));
        tableColumnTypeOfTraining.setCellValueFactory(new PropertyValueFactory("TypeOfTraining"));
        ObservableList<ProfileData> profileDataObservableList = FXCollections.observableList(trainees);
        tableViewTrainees.setItems(profileDataObservableList);
    }

    /**
     * Opens up a dialog and returns {@code true} if user confirmed action, {@code false} if not
     * @param name - Name of the trainee that is to be deleted
     * @return {@code true} if user confirmed action, {@code false} if he did not
     */
    private boolean getAddTraineeDialogAnswer(String name) {
        AddTraineeDialog addTraineeDialog = new AddTraineeDialog(Alert.AlertType.CONFIRMATION, name);
        Optional<ButtonType> result = addTraineeDialog.showAndWait();
        return result.get() == ButtonType.OK;
    }
    @Override
    protected void addingNotification(String email){
        String subject = "Trainer Notification";
        String content ="<div><b> Hi , the trainer <strong style=\"color:blue;\">"+TrainerUsername+"</strong> was added you into his training group.<br> If you have some questions contact him by email : <strong style=\"color:blue;\">"+TrainerEmail+"</strong></b><br></div> <prestyle=\"font-size:5px;\">"+picture+"</pre>";

        sendEmail(email,subject,content);
    }


    String picture ="___________________▄▄▄▀▀▀▀▀▀▀▄\n" +
            " _______________▄▀▀____▀▀▀▀▄____█\n" +
            " ___________▄▀▀__▀▀▀▀▀▀▄___▀▄___█\n" +
            " __________█▄▄▄▄▄▄_______▀▄__▀▄__█\n" +
            " _________█_________▀▄______█____█_█\n" +
            " ______▄█_____________▀▄_____▐___▐_▌\n" +
            " ______██_______________▀▄___▐_▄▀▀▀▄\n" +
            " ______█________██_______▌__▐▄▀______█\n" +
            " ______█_________█_______▌__▐▐________▐\n" +
            " _____▐__________▌_____▄▀▀▀__▌_______▐_____________▄▄▄▄▄▄\n" +
            " ______▌__________▀▀▀▀________▀▀▄▄▄▀______▄▄████▓▓▓▓▓▓▓███▄\n" +
            " ______▌____________________________▄▀__▄▄█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▄\n" +
            " ______▐__________________________▄▀_▄█▓▓▓▓▓▓▓▓▓▓_____▓▓____▓▓█▄\n" +
            " _______▌______________________▄▀_▄█▓▓▓▓▓▓▓▓▓▓▓____▓▓_▓▓_▓▓__▓▓█\n" +
            " _____▄▀▄_________________▄▀▀▌██▓▓▓▓▓▓▓▓▓▓▓▓▓__▓▓▓___▓▓_▓▓__▓▓█\n" +
            " ____▌____▀▀▀▄▄▄▄▄▄▄▄▀▀___▌█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓__▓________▓▓___▓▓▓█\n" +
            " _____▀▄_________________▄▀▀▓▓▓▓▓▓▓▓█████████████▄▄_____▓▓__▓▓▓█\n" +
            " _______█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▄▄___▓▓▓▓▓█\n" +
            " _______█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓███▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓█\n" +
            " ________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓█▓▓██░░███████░██▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓█\n" +
            " ________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓░░░░░█░░░░░██░░░░██▓▓▓▓▓▓▓▓▓██▓▓▓▓▌\n" +
            " ________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓███░░░░░░░░____░██░░░░░░░██▓▓▓▓▓▓▓██▓▓▌\n" +
            " ________▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░░________░░░░░░░░░██████▓▓▓▓▓█▓▌\n" +
            " ________▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░___▓▓▓▓▓░░░░░░░███░░███▓▓▓▓▓█▓▌\n" +
            " _________█▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░___▓▓█▄▄▓░░░░░░░░___░░░░█▓▓▓▓▓█▓▌\n" +
            " _________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░░█░░░___▓▓██░░░░░░░░▓▓▓▓__░░░░█▓▓▓▓██\n" +
            " _________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░███░░____▓░░░░░░░░░░░█▄█▓__░░░░█▓▓█▓█\n" +
            " _________▐▓▓▓▓▓▓▓▓▓▓▓▓▓█░█████░░░░░░░░░░░░░░░░░█▓__░░░░███▓█\n" +
            " __________█▓▓▓▓▓▓▓▓▓▓▓▓█░░███████░░░░░░░░░░░░░░░▓_░░░░░██▓█\n" +
            " __________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░███████░░░░░░░░░░░░░░_░░░░░██▓█\n" +
            " __________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░███████░░░░░░░░░░░░░░░░░░░██▓█\n" +
            " ___________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░░███████░░░░░░░░░░░█████░██░░░\n" +
            " ___________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░__███████░░░░░███████░░█░░░░\n" +
            " ___________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░█▄▄▄▀▀▀▀████████████░░█░░░░\n" +
            " ___________▐▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░██████▄__▀▀░░░███░░░░░█░░░\n" +
            " ___________▐▓▓▓▓▓▓▓▓▓▓▓█▒█░░░░░░▓▓▓▓▓███▄░░░░░░░░░░░░░░░______▄▄▄\n" +
            " ___________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█░░░░░░▓▓▓▓▓█░░░░░░░░░░░░░░░▄▄▄_▄▀▀____▀▄\n" +
            " __________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█▓▓░░░░░░░░░░░░░░░░░░░░░____▄▀____▀▄_________▀▄\n" +
            " _________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█▓▓▓▓░░░░░░░░░░░░░░░░░______▐▄________█▄▄▀▀▀▄__█\n" +
            " ________█▓▓▓▓▓▓▓▓█▒▒▒▒▒▒█▓▓▓▓▓▓▓░░░░░░░░░____________█_█______▐_________▀▄▌\n" +
            " _______█▓▓▓▓▓▓▓▓█▒▒▒▒▒▒███▓▓▓▓▓▓▓▓▓▓▓█▒▒▄___________█__▀▄____█____▄▄▄____▐\n" +
            " ______█▓▓▓▓▓▓▓█_______▒▒█▒▒██▓▓▓▓▓▓▓▓▓▓█▒▒▒▄_________█____▀▀█▀▄▀▀▀___▀▀▄▄▐\n" +
            " _____█▓▓▓▓▓██▒_________▒█▒▒▒▒▒███▓▓▓▓▓▓█▒▒▒██________▐_______▀█_____________█\n" +
            " ____█▓▓████▒█▒_________▒█▒▒▒▒▒▒▒▒███████▒▒▒▒██_______█_______▐______▄▄▄_____█\n" +
            " __█▒██▒▒▒▒▒▒█▒▒____▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒____▒█▓█__▄█__█______▀▄▄▀▀____▀▀▄▄█\n" +
            " __█▒▒▒▒▒▒▒▒▒▒█▒▒▒████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▓▓█▓▓▌_▐________▐____________▐\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒_______█▓▓▓█▓▌__▌_______▐_____▄▄____▐\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒_____█▓▓▓█▓▓▌__▌_______▀▄▄▀______▐\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███████▓▓█▓▓▓▌__▀▄_______________▄▀\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒██▓▓▓▓▓▌___▀▄_________▄▀▀\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒█▓▓▓▓▓▀▄__▀▄▄█▀▀▀\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▓▓▓██▄▄▄▀\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▄▄▄▄▄\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒██▄▄\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒█▄\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " ___█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒▌\n" +
            " ____█▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░▒▒▌\n" +
            " ____█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████████████▒▒▒▒▒█▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒▒▒▒▒▒░▒▌\n" +
            " _____█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______▐▒▒▒▒█▒▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌\n" +
            " ______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒█▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌\n" +
            " _______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒█▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌\n" +
            " ________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " _________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " _________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀\n" +
            " __________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒░░░▒▒▒▒▒░░░░░░░░▒▒▒█▀▀▀\n" +
            " ___________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░█▀\n" +
            " ____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀\n" +
            " _____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒▒▒▒▒▒▒▒▒▒▒▒█▀\n" +
            " _____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______▀▀▀███████▀▀\n" +
            " ______________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " _______________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " ________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " _________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " __________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒█\n" +
            " ___________________█▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒█\n" +
            " ___________________█▒▒▒▒▒▒▒▒████▒▒▒▒▒▒▒█\n" +
            " ___________________█████████▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " ____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " ____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█\n" +
            " _____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌\n" +
            " _____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌\n" +
            " ______________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▌\n" +
            " _______________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░█\n" +
            " ________________________█▒▒▒▒▒▒▒▒▒▒▒░░░█\n" +
            " __________________________██▒▒▒▒▒▒░░░█▀\n" +
            " _____________________________█░░░░░█▀\n" +
            " _______________________________▀▀▀▀";


}
