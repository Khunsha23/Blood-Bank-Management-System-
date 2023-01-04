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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BankLocationControl implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableColumn<BloodBanks, String> Areacl;

    @FXML
    private TableColumn<BloodBanks, String> CityCl;

    @FXML
    private TableView<BloodBanks> TableViewLcl;

    @FXML
    private TextField filterField;

    public void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField FieldForBank;

    @FXML
    public void ShowSelected(MouseEvent event) {
        ListM = TableViewLcl.getSelectionModel().getSelectedItems();
        if(ListM.size() == 0){
        }else {
            for(int i = 0; i < ListM.size(); i++){
                FieldForBank.setText(String.valueOf(ListM.get(0).Area));
            }
        }

    }
        @FXML
    void search_user() {
        CityCl.setCellValueFactory(new PropertyValueFactory<>("City"));
        Areacl.setCellValueFactory(new PropertyValueFactory<>("Area"));
        ListM = MySqlConnection.BloodBanks();
        TableViewLcl.setItems(ListM);
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
        sortedData.comparatorProperty().bind(TableViewLcl.comparatorProperty());
        TableViewLcl.setItems(sortedData);
    }
    ObservableList<BloodBanks> ListM;
    public void loadData() {
        CityCl.setCellValueFactory(new PropertyValueFactory<>("City"));
        Areacl.setCellValueFactory(new PropertyValueFactory<>("Area"));
        ListM = MySqlConnection.BloodBanks();
        TableViewLcl.setItems(ListM);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        search_user();
    }

}
