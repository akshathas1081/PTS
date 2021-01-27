package Control;

import Model.DBmanager;
import Model.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ReadReviewController {

    DBmanager dbm;

    private List<Review> reviewList = new LinkedList<>();

    @FXML
    private TableView<Review> tvReview;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();
        reviewList = dbm.getReviewList();
        setTvReviewCol();
        setTvReview();
    }

    private void setTvReviewCol() {
        TableColumn<Review, String> mailCol = new TableColumn<>("Email");
        mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Review, Integer> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingCol.setMaxWidth(50);

        TableColumn<Review, String> messageCol = new TableColumn<>("Message");
        messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        messageCol.setMaxWidth(170);

        tvReview.getColumns().setAll(mailCol, ratingCol, messageCol);
    }

    private void setTvReview() {
        ObservableList<Review> items = FXCollections.observableArrayList(reviewList);
        tvReview.setItems(items);
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
