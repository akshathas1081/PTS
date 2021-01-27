package Control;

import Model.DBmanager;
import Model.Review;
import Model.UserInfo;
import Utilities.RandomId;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WriteReviewController {

    DBmanager dbm;

    @FXML
    TextArea taMessage;
    @FXML
    Slider sliderRating;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();
    }

    public void sendBtn(ActionEvent event) {
        Review review = new Review(RandomId.getId(),taMessage.getText(), (int)sliderRating.getValue(), UserInfo.getUser().getEmail());
        dbm.writeReview(review);
        sliderRating.setValue(0);
        taMessage.clear();
    }

    public void backBtn(MouseEvent mouseEvent) {
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
