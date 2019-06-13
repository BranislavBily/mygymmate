package sample.Controllers.PopUpWindowControllers;

import db.DatabaseModuleUser;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import sample.Controllers.SceneControllers.Controller;
import sample.Controllers.SceneControllers.LoginRegister.LoginController;

public class SendMotivationEmailController extends TraineeInfoController {
    public TextField subjectField;
    public Label labelEmptyTextArea;
    public Label labelEmptySubject;
    public TextArea textareaMessage;

    private String TrainerEmail= LoginController.LoggedUserEmail;
    private String TrainerUsername = LoginController.LoggedUserName;
    String traineeEmail;


    public void onCreate(int traineeID) {
        DatabaseModuleUser databaseModuleUser = new DatabaseModuleUser();
        this.traineeEmail=databaseModuleUser.getUserEmailByUserID(traineeID);
        System.out.println(traineeEmail);


    }



    public void onButtonSendPressed() {
        if(subjectField.getText().isEmpty() && textareaMessage.getText().isEmpty()) {
            labelEmptySubject.setVisible(true);
            labelEmptyTextArea.setVisible(true);
            System.out.println("both are empty");

        }
        else if(subjectField.getText().isEmpty()) {
            labelEmptySubject.setVisible(true);
            System.out.println("The subject is empty");
        } else if (textareaMessage.getText().isEmpty()) {
            labelEmptyTextArea.setVisible(true);
            System.out.println("The text Area is empty");
        }else{
            Stage stage = (Stage) subjectField.getScene().getWindow();
            addingNotification(traineeEmail);
            stage.close();
            System.out.println("Motivation email was send");
        }

    }

    private void resetAllFeedback() {

        labelEmptySubject.setVisible(false);
        labelEmptyTextArea.setVisible(false);

    }



    @Override
    protected void addingNotification(String email) {
        String content ="<div style=\"width:80%;\" ><b><pre>"+textareaMessage.getText()+"</pre></b><hr><br><b> This email has been sent by trainer  <strong style=\"color:blue;\">"+TrainerUsername+"</strong><br> If you have some questions contact him here: <strong style=\"color:blue;\">"+TrainerEmail+"</strong></b><br></div><br> <p style=\" font-size: 2px;\">"+picture+"</p>";

        sendEmail(email,subjectField.getText(),content);
    }


    String picture = "___________________▄▄▄▀▀▀▀▀▀▀▄<br>\n" +
            " _______________▄▀▀____▀▀▀▀▄____█<br>\n" +
            " ___________▄▀▀__▀▀▀▀▀▀▄___▀▄___█<br>\n" +
            " __________█▄▄▄▄▄▄_______▀▄__▀▄__█<br>\n" +
            " _________█_________▀▄______█____█_█<br>\n" +
            " ______▄█_____________▀▄_____▐___▐_▌<br>\n" +
            " ______██_______________▀▄___▐_▄▀▀▀▄<br>\n" +
            " ______█________██_______▌__▐▄▀______█<br>\n" +
            " ______█_________█_______▌__▐▐________▐<br>\n" +
            " _____▐__________▌_____▄▀▀▀__▌_______▐_____________▄▄▄▄▄▄<br>\n" +
            " ______▌__________▀▀▀▀________▀▀▄▄▄▀______▄▄████▓▓▓▓▓▓▓███▄<br>\n" +
            " ______▌____________________________▄▀__▄▄█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▄<br>\n" +
            " ______▐__________________________▄▀_▄█▓▓▓▓▓▓▓▓▓▓_____▓▓____▓▓█▄<br>\n" +
            " _______▌______________________▄▀_▄█▓▓▓▓▓▓▓▓▓▓▓____▓▓_▓▓_▓▓__▓▓█<br>\n" +
            " _____▄▀▄_________________▄▀▀▌██▓▓▓▓▓▓▓▓▓▓▓▓▓__▓▓▓___▓▓_▓▓__▓▓█<br>\n" +
            " ____▌____▀▀▀▄▄▄▄▄▄▄▄▀▀___▌█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓__▓________▓▓___▓▓▓█<br>\n" +
            " _____▀▄_________________▄▀▀▓▓▓▓▓▓▓▓█████████████▄▄_____▓▓__▓▓▓█<br>\n" +
            " _______█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▄▄___▓▓▓▓▓█<br>\n" +
            " _______█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓▓███▓▓▓▓████▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓█<br>\n" +
            " ________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓█▓█▓▓██░░███████░██▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓█<br>\n" +
            " ________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓░░░░░█░░░░░██░░░░██▓▓▓▓▓▓▓▓▓██▓▓▓▓▌<br>\n" +
            " ________█▓▓▓▓▓▓▓▓▓▓▓▓▓▓███░░░░░░░░____░██░░░░░░░██▓▓▓▓▓▓▓██▓▓▌<br>\n" +
            " ________▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░░________░░░░░░░░░██████▓▓▓▓▓█▓▌<br>\n" +
            " ________▐▓▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░░___▓▓▓▓▓░░░░░░░███░░███▓▓▓▓▓█▓▌<br>\n" +
            " _________█▓▓▓▓▓▓▓▓▓▓▓▓▓██░░░░░___▓▓█▄▄▓░░░░░░░░___░░░░█▓▓▓▓▓█▓▌<br>\n" +
            " _________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░░█░░░___▓▓██░░░░░░░░▓▓▓▓__░░░░█▓▓▓▓██<br>\n" +
            " _________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░███░░____▓░░░░░░░░░░░█▄█▓__░░░░█▓▓█▓█<br>\n" +
            " _________▐▓▓▓▓▓▓▓▓▓▓▓▓▓█░█████░░░░░░░░░░░░░░░░░█▓__░░░░███▓█<br>\n" +
            " __________█▓▓▓▓▓▓▓▓▓▓▓▓█░░███████░░░░░░░░░░░░░░░▓_░░░░░██▓█<br>\n" +
            " __________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░███████░░░░░░░░░░░░░░_░░░░░██▓█<br>\n" +
            " __________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░███████░░░░░░░░░░░░░░░░░░░██▓█<br>\n" +
            " ___________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░░███████░░░░░░░░░░░█████░██░░░<br>\n" +
            " ___________█▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░__███████░░░░░███████░░█░░░░<br>\n" +
            " ___________█▓▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░█▄▄▄▀▀▀▀████████████░░█░░░░<br>\n" +
            " ___________▐▓▓▓▓▓▓▓▓▓▓▓▓█░░░░░░██████▄__▀▀░░░███░░░░░█░░░<br>\n" +
            " ___________▐▓▓▓▓▓▓▓▓▓▓▓█▒█░░░░░░▓▓▓▓▓███▄░░░░░░░░░░░░░░░______▄▄▄<br>\n" +
            " ___________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█░░░░░░▓▓▓▓▓█░░░░░░░░░░░░░░░▄▄▄_▄▀▀____▀▄<br>\n" +
            " __________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█▓▓░░░░░░░░░░░░░░░░░░░░░____▄▀____▀▄_________▀▄<br>\n" +
            " _________█▓▓▓▓▓▓▓▓▓█▒▒▒▒█▓▓▓▓░░░░░░░░░░░░░░░░░______▐▄________█▄▄▀▀▀▄__█<br>\n" +
            " ________█▓▓▓▓▓▓▓▓█▒▒▒▒▒▒█▓▓▓▓▓▓▓░░░░░░░░░____________█_█______▐_________▀▄▌<br>\n" +
            " _______█▓▓▓▓▓▓▓▓█▒▒▒▒▒▒███▓▓▓▓▓▓▓▓▓▓▓█▒▒▄___________█__▀▄____█____▄▄▄____▐<br>\n" +
            " ______█▓▓▓▓▓▓▓█_______▒▒█▒▒██▓▓▓▓▓▓▓▓▓▓█▒▒▒▄_________█____▀▀█▀▄▀▀▀___▀▀▄▄▐<br>\n" +
            " _____█▓▓▓▓▓██▒_________▒█▒▒▒▒▒███▓▓▓▓▓▓█▒▒▒██________▐_______▀█_____________█<br>\n" +
            " ____█▓▓████▒█▒_________▒█▒▒▒▒▒▒▒▒███████▒▒▒▒██_______█_______▐______▄▄▄_____█<br>\n" +
            " __█▒██▒▒▒▒▒▒█▒▒____▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒____▒█▓█__▄█__█______▀▄▄▀▀____▀▀▄▄█<br>\n" +
            " __█▒▒▒▒▒▒▒▒▒▒█▒▒▒████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▓▓█▓▓▌_▐________▐____________▐<br>\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒_______█▓▓▓█▓▌__▌_______▐_____▄▄____▐<br>\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒_____█▓▓▓█▓▓▌__▌_______▀▄▄▀______▐<br>\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███████▓▓█▓▓▓▌__▀▄_______________▄▀<br>\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒██▓▓▓▓▓▌___▀▄_________▄▀▀<br>\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒█▓▓▓▓▓▀▄__▀▄▄█▀▀▀<br>\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▓▓▓██▄▄▄▀<br>\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████<br>\n" +
            " █▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▄▄▄▄▄<br>\n" +
            " _█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒██▄▄<br>\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒█▄<br>\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " __█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " ___█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒▌<br>\n" +
            " ____█▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒▒▒▒█▒▒▒▒█▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░▒▒▌<br>\n" +
            " ____█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█████████████▒▒▒▒▒█▒▒▒▒▒▒▒▒░░░░▒▒▒▒▒▒▒▒▒▒▒░▒▌<br>\n" +
            " _____█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______▐▒▒▒▒█▒▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌<br>\n" +
            " ______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒█▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌<br>\n" +
            " _______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒█▒▒▒▒▒▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▌<br>\n" +
            " ________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " _________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " _________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█________█▒▒▒░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀<br>\n" +
            " __________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒░░░▒▒▒▒▒░░░░░░░░▒▒▒█▀▀▀<br>\n" +
            " ___________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░█▀<br>\n" +
            " ____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▀<br>\n" +
            " _____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______█▒▒▒▒▒▒▒▒▒▒▒▒█▀<br>\n" +
            " _____________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█_______▀▀▀███████▀▀<br>\n" +
            " ______________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " _______________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " ________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " _________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " __________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▒█<br>\n" +
            " ___________________█▒▒▒▒▒▒▒▒▒▒▒▒▒██▒▒▒▒█<br>\n" +
            " ___________________█▒▒▒▒▒▒▒▒████▒▒▒▒▒▒▒█<br>\n" +
            " ___________________█████████▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " ____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " ____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█<br>\n" +
            " _____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌<br>\n" +
            " _____________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▌<br>\n" +
            " ______________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▌<br>\n" +
            " _______________________█▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░█<br>\n" +
            " ________________________█▒▒▒▒▒▒▒▒▒▒▒░░░█<br>\n" +
            " __________________________██▒▒▒▒▒▒░░░█▀<br>\n" +
            " _____________________________█░░░░░█▀<br>\n" +
            " _______________________________▀▀▀▀<br>";
}
