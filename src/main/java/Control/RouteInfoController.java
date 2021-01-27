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
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RouteInfoController {

    private DBmanager dbm;

    private List<Route> routeList;

    @FXML
    ComboBox<Route> cmbRoute;

    @FXML
    TableView<RouteIncludes> tvStop;
    @FXML
    ListView<String> lvDeparture0;
    @FXML
    ListView<String> lvDeparture1;
    @FXML
    TableView<TransportPrice> tvPrice;

    @FXML
    Label lblId;

    @FXML
    public void initialize() {
        dbm = DBmanager.getInstance();

        routeList = dbm.getRouteList(true, true, true);

        ObservableList<Route> items = FXCollections.observableArrayList(routeList);
        cmbRoute.getItems().addAll(items);

        setTvPriceCol();
        setTvStopCol();
    }

    public void cmbAction(ActionEvent event) {
        setLabels(cmbRoute.getValue());
        setTvPrice(cmbRoute.getValue());
        setTvStop(cmbRoute.getValue());
        setLvDeparture(cmbRoute.getValue());
    }

    private void setLabels(Route route) {
        lblId.setText(route.getId());
    }

    private void setTvStop(Route route) {
        route.getIncludesList().sort(Comparator.naturalOrder());
        ObservableList<RouteIncludes> items = FXCollections.observableArrayList(route.getIncludesList());
        tvStop.setItems(items);
    }

    private void setTvStopCol() {
        TableColumn<RouteIncludes, Integer> orderCol = new TableColumn<>("Visit Oreder");
        orderCol.setCellValueFactory(new PropertyValueFactory<>("oreder"));
        orderCol.setMinWidth(80);

        TableColumn<RouteIncludes, Stop> stopCol = new TableColumn<>("Stop");
        stopCol.setCellValueFactory(new PropertyValueFactory<>("stop"));
        orderCol.setMinWidth(120);

        tvStop.getColumns().addAll(orderCol, stopCol);
    }

    private void setLvDeparture(Route route) {
        lvDeparture0.setItems(FXCollections.observableArrayList(route.getDepartures0()));
        lvDeparture1.setItems(FXCollections.observableArrayList(route.getDepartures1()));
    }

    private void setTvPriceCol() {
        TableColumn<TransportPrice, String> typeCol = new TableColumn<>("Card Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("cardType"));
        typeCol.setMinWidth(120);

        TableColumn<TransportPrice, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        tvPrice.getColumns().addAll(typeCol, priceCol);
    }

    private void setTvPrice(Route route) {
        ObservableList<TransportPrice> items = FXCollections.observableArrayList(route.getPriceList());
        tvPrice.setItems(items);
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
