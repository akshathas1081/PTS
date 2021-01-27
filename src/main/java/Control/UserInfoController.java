package Control;

import Model.*;
import Utilities.AlertOp;
import Utilities.RandomId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInfoController {

    private DBmanager dbm;
    private User user;
    private Passenger passenger;

    @FXML
    private Label lblMail;
    @FXML
    private Label lblId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblBdate;

    @FXML
    private TableView<Address> tvAddress;
    @FXML
    private TableView<Phone> tvPhone;
    @FXML
    private TableView<Review> tvReview;

    @FXML
    private TextArea taMessage;
    @FXML
    private TextField tfRating;

    @FXML
    private TextField tfTown;
    @FXML
    private TextField tfNbhd;
    @FXML
    private TextField tfStreet;
    @FXML
    private TextField tfNumber;
    @FXML
    private TextField tfCoorX;
    @FXML
    private TextField tfCoorY;

    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfPhoneType;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();
        user = UserInfo.getUser();
        dbm.setUser(user.getEmail(), user.getPassword());
        passenger = UserInfo.getPassenger();
        setLabels();
        setTvAddressCol();
        setTvAddress();
        setTvPhoneCol();
        setTvPhone();
        setTvReviewCol();
        setTvReview();
    }

    private void setLabels() {
        lblMail.setText(user.getEmail());
        lblId.setText(passenger.getPassingerId());
        lblName.setText(passenger.getFullName());
        lblBdate.setText(passenger.getBirthDate());
    }

    private void setTvAddressCol() {

        TableColumn<Address, String> townCol = new TableColumn<>("Town");
        townCol.setCellValueFactory(new PropertyValueFactory<>("town"));

        TableColumn<Address, String> nbhdCol = new TableColumn<>("Neighborhood");
        nbhdCol.setCellValueFactory(new PropertyValueFactory<>("neighborhood"));

        TableColumn<Address, String> streetCol = new TableColumn<>("Street");
        streetCol.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<Address, String> numCol = new TableColumn<>("Num");
        numCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        numCol.setPrefWidth(40);

        TableColumn<Address, Integer> xCol = new TableColumn<>("X");
        xCol.setCellValueFactory(new PropertyValueFactory<>("coorX"));
        xCol.setPrefWidth(30);

        TableColumn<Address, Integer> yCol = new TableColumn<>("Y");
        yCol.setCellValueFactory(new PropertyValueFactory<>("coorY"));
        yCol.setPrefWidth(30);

        tvAddress.getColumns().addAll(townCol, nbhdCol, streetCol, numCol, xCol, yCol);
    }

    private void setTvAddress() {
        if (!UserInfo.getPassenger().getAddressList().isEmpty()) {
            ObservableList<Address> items = FXCollections.observableArrayList(UserInfo.getPassenger().getAddressList());
            tvAddress.setItems(items);
        }
    }

    private void setTvPhoneCol() {
        TableColumn<Phone, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Phone, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("phoneType"));

        tvPhone.getColumns().setAll(phoneCol, typeCol);
    }

    private void setTvPhone() {
        if  (!UserInfo.getPassenger().getPhoneList().isEmpty()) {
            ObservableList<Phone> items = FXCollections.observableArrayList(UserInfo.getPassenger().getPhoneList());
            tvPhone.setItems(items);
        }
    }

    private void setTvReviewCol() {
        TableColumn<Review, Integer> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        TableColumn<Review, String> messageCol = new TableColumn<>("Message");
        messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        messageCol.setMaxWidth(365);

        tvReview.getColumns().setAll(ratingCol, messageCol);
    }

    private void setTvReview() {
        if(!UserInfo.getUser().getReviewList().isEmpty()) {
            ObservableList<Review> items = FXCollections.observableArrayList(UserInfo.getUser().getReviewList());
            tvReview.setItems(items);
        }
    }

    public void tvPhone(MouseEvent event) {
        Phone phone = tvPhone.getSelectionModel().getSelectedItem();
        tfPhone.setText(phone.getPhone());
        tfPhoneType.setText(phone.getPhoneType());
    }

    public void phoneAddBtn(ActionEvent event) {
        Phone phone = new Phone();
        phone.setPhone(tfPhone.getText());
        phone.setPhoneType(tfPhoneType.getText());
        tvPhone.getItems().add(phone);
        dbm.addPhone(phone, UserInfo.getPassenger().getPassingerId());
        tvPhone.refresh();
    }

    public void phoneSetBtn(ActionEvent event) {
        Phone phone = tvPhone.getSelectionModel().getSelectedItem();
        String oldPhone = phone.getPhone();
        phone.setPhone(tfPhone.getText());
        phone.setPhoneType(tfPhoneType.getText());
        dbm.setPhone(phone, oldPhone, UserInfo.getPassenger().getPassingerId());
        tvPhone.refresh();
    }

    public void phoneRemoveBtn(ActionEvent event) {
        if (AlertOp.confirm("Confirmation", "Remove Operation",
                "Are you sure you want to remove this phone?")) {
            ObservableList<Phone> selected, all;
            all = tvPhone.getItems();
            selected = tvPhone.getSelectionModel().getSelectedItems();

            for (Phone phone : selected) {
                dbm.removePhone(phone, UserInfo.getPassenger().getPassingerId());
            }

            selected.forEach(all::remove);

            tfTown.clear();
            tfNbhd.clear();
            tfStreet.clear();
            tfNumber.clear();
            tfCoorX.clear();
            tfCoorY.clear();

            tvReview.refresh();
        }
    }

    public void tvAddress(MouseEvent event) {
        Address address = tvAddress.getSelectionModel().getSelectedItem();
        tfTown.setText(address.getTown());
        tfNbhd.setText(address.getNeighborhood());
        tfStreet.setText(address.getStreet());
        tfNumber.setText(address.getNumber());
        tfCoorX.setText(String.valueOf(address.getCoorX()));
        tfCoorY.setText(String.valueOf(address.getCoorY()));
    }

    public void addressAddBtn(ActionEvent event) {
        Address address = new Address();
        address.setId(RandomId.getId());
        address.setTown(tfTown.getText());
        address.setNeighborhood(tfNbhd.getText());
        address.setStreet(tfStreet.getText());
        address.setCoorX(Integer.valueOf(tfCoorX.getText()));
        address.setCoorY(Integer.valueOf(tfCoorY.getText()));
        tvAddress.getItems().add(address);
        dbm.addAddress(address, UserInfo.getPassenger().getPassingerId());
        tvAddress.refresh();
    }

    public void addressSetBtn(ActionEvent event) {
        Address address = tvAddress.getSelectionModel().getSelectedItem();
        address.setTown(tfTown.getText());
        address.setNeighborhood(tfNbhd.getText());
        address.setStreet(tfStreet.getText());
        address.setCoorX(Integer.valueOf(tfCoorX.getText()));
        address.setCoorY(Integer.valueOf(tfCoorY.getText()));
        dbm.setAddress(address);
        tvAddress.refresh();
    }

    public void addressRemoveBtn(ActionEvent event) {
        if (AlertOp.confirm("Confirmation", "Remove Operation",
                "Are you sure you want to remove this address?")) {
            ObservableList<Address> selected, all;
            all = tvAddress.getItems();
            selected = tvAddress.getSelectionModel().getSelectedItems();

            for (Address address : selected) {
                dbm.removeAddress(address);
            }

            selected.forEach(all::remove);

            tfTown.clear();
            tfNbhd.clear();
            tfStreet.clear();
            tfNumber.clear();
            tfCoorX.clear();
            tfCoorY.clear();

            tvReview.refresh();
        }
    }

    public void tvReview(MouseEvent event) {
        Review review = tvReview.getSelectionModel().getSelectedItem();
        taMessage.setText(review.getMessage());
        tfRating.setText(String.valueOf(review.getRating()));
    }

    public void reviewSetBtn(ActionEvent event) {
        Review review = tvReview.getSelectionModel().getSelectedItem();
        review.setMessage(taMessage.getText());
        review.setRating(Integer.valueOf(tfRating.getText()));
        dbm.setReview(review);
        tvReview.refresh();
    }

    public void reviewRemoveBtn(ActionEvent event) {
        if (AlertOp.confirm("Confirmation", "Remove Operation",
                "Are you sure you want to remove this review?")) {
            ObservableList<Review> selected, all;
            all = tvReview.getItems();
            selected = tvReview.getSelectionModel().getSelectedItems();

            for (Review review : selected) {
                dbm.removeReview(review);
            }

            selected.forEach(all::remove);

            taMessage.clear();
            tfRating.clear();

            tvReview.refresh();
        }
    }

    public void backBtn(MouseEvent event) {
        user = UserInfo.getUser();
        dbm.setUser(user.getEmail(), user.getPassword());
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