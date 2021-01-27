package Control;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class StopInfoController {

    private DBmanager dbm;

    private List<Stop> stopList;

    @FXML
    private ComboBox<Stop> cmbStop;

    @FXML
    private ListView<Route> lvRoute;
    @FXML ListView<String> lvAddress;

    @FXML
    private Label lblName;
    @FXML
    private Label lblTown;
    @FXML
    private Label lblNbhd;
    @FXML
    private Label lblStreet;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();

        stopList = dbm.getStopList(true);

        ObservableList<Stop> items = FXCollections.observableArrayList(stopList);
        cmbStop.getItems().addAll(items);
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

    public void cmbAction(ActionEvent actionEvent) {
        setLabels(cmbStop.getValue());
        setLv(cmbStop.getValue());
        setLvAddress(cmbStop.getValue());
    }

    private void setLabels(Stop stop) {
        lblName.setText(stop.getName());
        Address address = stop.getAddress();
        lblTown.setText(address.getTown());
        lblNbhd.setText(address.getNeighborhood());
        lblStreet.setText(address.getStreet());
    }

    private void setLv(Stop stop) {
        ObservableList<Route> items = FXCollections.observableArrayList(stop.getRouteList());
        lvRoute.setItems(items);
    }

    private void setLvAddress(Stop stop) {
        int x1 = stop.getAddress().getCoorX();
        int y1 = stop.getAddress().getCoorY();
        List<Address> addressList = UserInfo.getPassenger().getAddressList();
        List<String> items = new LinkedList<>();
        if (!addressList.isEmpty()) {
            for (Address address: addressList) {
                String item = address.getTown() + "/"
                        + address.getNeighborhood() + "/"
                        + address.getStreet() + "/"
                        + address.getNumber() + " ";
                int x2 = address.getCoorX();
                int y2 = address.getCoorY();

                long dist = Math.round(Math.sqrt(Math.abs((x1 - x2)*(x1 - x2) - (y1 - y2)*(y1 - y2))));
                item += "| Distance : " + dist + "m";
                items.add(item);
            }
        }
        lvAddress.getItems().setAll(items);
    }

}