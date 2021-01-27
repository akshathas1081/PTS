package Control;

import Model.Card;
import Model.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CardInfoController {

    private Card card;

    @FXML
    Label lblId;
    @FXML
    Label lblType;
    @FXML
    Label lblStatus;
    @FXML
    Label lblUsage;
    @FXML
    Label lblCount;
    @FXML
    Label lblIssue;
    @FXML
    Label lblBalance;

    @FXML
    public void initialize() {
        card = UserInfo.getCard();
        setLabels();
    }

    private void setLabels() {
        lblId.setText(card.getCardId());
        lblType.setText(card.getCardType());
        lblStatus.setText(card.getStatus());
        lblUsage.setText(card.getLastUsage());
        lblCount.setText(String.valueOf(card.getTravelCount()));
        lblIssue.setText(card.getIssueDate());
        lblBalance.setText(String.valueOf(card.getBalance()));
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
