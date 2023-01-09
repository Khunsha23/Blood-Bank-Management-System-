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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        refresh();
        stage.show();
    }
    @FXML
    private TextField City;
    @FXML
    private TextField Area;
    @FXML
    private Label Error;
    public void AddLocation(ActionEvent event) throws SQLException {
        String c= City.getText();
        String a= Area.getText();
        Connection conn = MySqlConnection.ConnectDB();
        if(c.equals("Lahore")||c.equals("Karachi")||c.equals("Islamabad")) {
            if (c.equals("Lahore")) {
                String query = "INSERT INTO bloodcamps(City,Area)VALUE(?,?)";
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, c);
                stm.setString(2, a);
                stm.executeUpdate();
                loadData();
                refresh();
                loadData();
            } else if (c.equals("Karachi")) {
                String query = "INSERT INTO bloodcamps(City,Area)VALUE(?,?)";
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, c);
                stm.setString(2, a);
                stm.executeUpdate();
                loadData();
                refresh();
                loadData();

            } else if (c.equals("Islamabad")) {
                String query = "INSERT INTO bloodcamps(City,Area)VALUE(?,?)";
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, c);
                stm.setString(2, a);
                stm.executeUpdate();
                loadData();
                refresh();
                loadData();

            }
        }
        else if(!(c.equals("Lahore")||c.equals("Karachi")||c.equals("Islamabad"))){
            Error.setText("Invalid City");
        }
    }
    public void DeleteLocation(ActionEvent event) throws SQLException {
        String c= City.getText();
        String a= Area.getText();
        Connection conn = MySqlConnection.ConnectDB();
            if (c.equals("Lahore")) {
                String query = "DELETE FROM bloodcamps WHERE City=? AND Area=?";
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, c);
                stm.setString(2, a);
                stm.executeUpdate();
                loadData();
                refresh();
                loadData();
            } else if (c.equals("Karachi")) {
                String query = "DELETE FROM bloodcamps WHERE City=? AND Area=?";
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, c);
                stm.setString(2, a);
                stm.executeUpdate();
                loadData();
                refresh();
                loadData();

            } else if (c.equals("Islamabad")) {
                System.out.println("h");
                String query = "DELETE FROM bloodcamps WHERE City=? AND Area=?";
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, c);
                stm.setString(2, a);
                stm.executeUpdate();
                loadData();
                refresh();
                loadData();

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
    public void refresh(){
        ListM.clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        search_user();
    }

}