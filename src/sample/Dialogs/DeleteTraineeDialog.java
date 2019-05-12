package sample.Dialogs;

import db.DTO.ProfileData;
import javafx.scene.control.Alert;

public class DeleteTraineeDialog extends Alert {

    public DeleteTraineeDialog(Alert.AlertType alertType, ProfileData profileData) {
        super(alertType);
        setTitle("Delete Trainee");
        setHeaderText("Are you sure you want to delete " + profileData.getRealName() + " from your list?");
        setContentText("This action can not be undone!");
    }
}
