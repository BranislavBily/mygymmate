package sample.Dialogs;

import javafx.scene.control.Alert;

public class WrongPasswordDialog extends Alert {

    public WrongPasswordDialog(AlertType alertType) {
        super(alertType);
        super.setTitle("Wrong password");
        super.setHeaderText("No action will be done!");
        super.showAndWait();
    }

}
