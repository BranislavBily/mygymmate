package sample.Controllers.SceneControllers.LoginRegister;

import db.DTO.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import sample.Controllers.SceneControllers.LoginRegistrationController;

public class RegisterVerifyController extends LoginRegistrationController {


    private User user;


    @FXML
    private TextField textFieldVerificationCode;
    @FXML
    private Button buttonCheckCode;
    @FXML
    private Label labelUsername;
    @FXML
    private Button buttonGoBack;
    @FXML
    private Label mandatoryError;
    @FXML
    private Hyperlink hyperLinkSendNewCode;
    @FXML
    public Label codeIsNotCorrect;

    /**
     * Prepares scene for use
     *
     * @param user data from previous scene
     */
    public void onCreate(User user) {
        this.user = user;
        SetParameters();


        setLabelUsernameVerify(user.getUsername());
    }

    private void SetParameters() {
        email = user.getEmail();
        code = user.getCode();
        username = user.getUsername();
        password = user.getPassword();
    }

    private String email;
    private String code;
    private String inputCode;
    private String username;
    private String password;


    public void onButtonCheckCodePressed() {
        resetAllFeedback();
        System.out.println("The code in register verify is : "+code);
        inputCode = textFieldVerificationCode.getText();

        System.out.println("The inputed code is : "+inputCode);
        if (inputCode == null || inputCode.equals("")) {
            displayFeedBack(textFieldVerificationCode);
            mandatoryError.setVisible(true);
            System.out.println("This field is mandatory");

        } else if (!inputCode.equals(code)) {
            System.out.print("Enter correct validation code");
            displayFeedBack(textFieldVerificationCode);
            codeIsNotCorrect.setVisible(true);

        } else {
            User user = new User(username, password, email, code);
            setSceneToRegisterInfo(textFieldVerificationCode.getScene(), user);
        }
    }

    private void resetAllFeedback() {

        mandatoryError.setVisible(false);
        DropShadow usernameShadow = getCleanDropShadow();
        codeIsNotCorrect.setVisible(false);
    }


    public void onHyperLinkSendNewCodePressed() {
        code = generateValidationCode(5);
        sendEmail(email, "Validation Code", "Hi your validation code is " + code);
    }

    private void setLabelUsernameVerify(String username) {
        labelUsername.setText("Hi " + username + "!");
        labelUsername.setLayoutX(182 - ((username.length() + 1) * 5.5));
    }

    @FXML
    private void onButtonGoBackPressed() {
        setSceneToRegister(buttonGoBack.getScene());
    }
}
