package Control;

import Model.DBmanager;
import Model.Login;
import Utilities.AlertOp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    DBmanager dbm;

    @FXML
    private TextField tfMail;
    @FXML
    private PasswordField pf;

    private String mail,pass;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();
    }

    public void randomBtn(ActionEvent event) {
        Login login = dbm.getRandomLogin();
        tfMail.setText(login.getEmail());
        pf.setText(login.getPassword());
    }

    public void loginBtn(ActionEvent event) {
        mail = tfMail.getText();
        pass = pf.getText();

        if (dbm.login(mail, pass)) {
            changeScene();
        }else {
            AlertOp.error("Login Error", "An error occurred while logging in",
                    "Email or Password is wrong!");
        }

    }

    private void changeScene() {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/MainMenu.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}