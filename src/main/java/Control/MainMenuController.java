package Control;

import Model.User;
import Model.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public void userBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/UserInfo.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void writeBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/WriteReview.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void readBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/ReadReview.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void stopBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/StopInfo.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void routeBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/RouteInfo.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void cardBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/CardInfo.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void transportBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/TransportInfo.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void logoutBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Login.fxml"));
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
