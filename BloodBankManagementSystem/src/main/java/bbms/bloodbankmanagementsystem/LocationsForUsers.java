package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public void SwitchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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
    public void switchToReceiverInfo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Receiverinfo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}