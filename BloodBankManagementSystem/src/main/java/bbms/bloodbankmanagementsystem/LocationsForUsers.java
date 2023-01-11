package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocationsForUsers implements Initializable {

    @FXML
    private TableColumn<BloodBanks,String> AreaColumn;

    @FXML
    private TableColumn<BloodBanks, String> CityColumn;

    @FXML
    private TableView<BloodBanks>TableViewLocations;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void SwitchToInfo(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("Receiverinfo.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader1.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader1.load());
        }
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<BloodBanks> ListM;
    public void loadData() {
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("City"));
        AreaColumn.setCellValueFactory(new PropertyValueFactory<>("Area"));
        ListM = MySqlConnection.BloodBanks();
        TableViewLocations.setItems(ListM);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        search_user();
    }
    @FXML
    private TextField filterField;
    @FXML
    void search_user() {
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("City"));
        AreaColumn.setCellValueFactory(new PropertyValueFactory<>("Area"));
        ListM = MySqlConnection.BloodBanks();
        TableViewLocations.setItems(ListM);
        FilteredList<BloodBanks> filteredData = new FilteredList<>(ListM, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Location -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (Location.getCity().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches City
                } else if (Location.getArea().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches Area
                } else
                    return false; // Does not match.
            });
        });
        SortedList<BloodBanks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableViewLocations.comparatorProperty());
        TableViewLocations.setItems(sortedData);
    }
    @FXML
    public void switchToDonorInfo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("Donorinfo.fxml"));
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        if (stage.isMaximized()) {
            scene = new Scene(fxmlLoader1.load(), screenSize.getWidth(), screenSize.getHeight());
        } else {
            scene = new Scene(fxmlLoader1.load());
        }
        stage.setScene(scene);
        stage.show();
    }

}