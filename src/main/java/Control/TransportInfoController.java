package Control;

import Model.*;
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
import java.util.LinkedList;
import java.util.List;

public class TransportInfoController {

    private DBmanager dbm;

    private List<Route> routeList;

    private OnTheMove d0;
    private OnTheMove d1;

    @FXML
    private ComboBox<Route> cmbRoute;

    @FXML
    private TableView<OnTheMove> tvD0;
    @FXML
    private TableView<OnTheMove> tvD1;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();

        routeList = dbm.getRouteList(false, false, false);

        ObservableList<Route> items = FXCollections.observableArrayList(routeList);
        cmbRoute.getItems().addAll(items);
        setTvD0Col();
        setTvD1Col();
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
        setTvD0(cmbRoute.getValue());
        setTvD1(cmbRoute.getValue());
    }

    private void setTvD0Col() {
        TableColumn<OnTheMove, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<OnTheMove, String> stopCol = new TableColumn<>("Last Stop");
        stopCol.setCellValueFactory(new PropertyValueFactory<>("lastStop"));

        TableColumn<OnTheMove, String> vehicleCol = new TableColumn<>("Vehicle Type");
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

        TableColumn<OnTheMove, Integer> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        TableColumn<OnTheMove, String> driverCol = new TableColumn<>("Driver Name");
        driverCol.setCellValueFactory(new PropertyValueFactory<>("driverName"));

        tvD0.getColumns().addAll(statusCol, stopCol, vehicleCol, capacityCol, driverCol);
    }

    private void setTvD1Col() {
        TableColumn<OnTheMove, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<OnTheMove, String> stopCol = new TableColumn<>("Last Stop");
        stopCol.setCellValueFactory(new PropertyValueFactory<>("lastStop"));

        TableColumn<OnTheMove, String> vehicleCol = new TableColumn<>("Vehicle Type");
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

        TableColumn<OnTheMove, Integer> capacityCol = new TableColumn<>("Capacity");
        capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        TableColumn<OnTheMove, String> driverCol = new TableColumn<>("Driver Name");
        driverCol.setCellValueFactory(new PropertyValueFactory<>("driverName"));

        tvD1.getColumns().addAll(statusCol, stopCol, vehicleCol, capacityCol, driverCol);
    }

    private void setTvD0(Route route) {
        d0 = dbm.getOnTheMove(route, false);
        ObservableList<OnTheMove> items = FXCollections.observableArrayList(d0);
        tvD0.setItems(items);
    }

    private void setTvD1(Route route) {
        d0 = dbm.getOnTheMove(route, true);
        ObservableList<OnTheMove> items = FXCollections.observableArrayList(d0);
        tvD1.setItems(items);
    }

}