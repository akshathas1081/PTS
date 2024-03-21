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
import java.util.*;
import java.lang.*;

public class StopInfoController {

    private DBmanager dbm;

    private List<Stop> stopList;

    @FXML
    private ComboBox<Stop> cmbStop;

    @FXML
    private ListView<Route> lvRoute;
    @FXML
    private ListView<String> lvAddress;

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
        double x1 = stop.getAddress().getCoorX();
        double y1 = stop.getAddress().getCoorY();
        List<Address> addressList = UserInfo.getPassenger().getAddressList();
        List<String> items = new LinkedList<>();
        if (!addressList.isEmpty()) {
            for (Address address : addressList) {
                String item = address.getTown() + "/"
                        + address.getNeighborhood() + "/"
                        + address.getStreet() + "/"
                        + address.getNumber() + " ";
                double x2 = address.getCoorX();
                double y2 = address.getCoorY();

                double dist = haversine(x1, y1, x2, y2);
                item += "| Distance : " + Math.round(dist * 1000) + "m"; // Convert distance to meters
                items.add(item);
            }
        }
        lvAddress.getItems().setAll(items);
    }

    // Haversine formula to calculate distance between two points given their latitude and longitude
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }
}