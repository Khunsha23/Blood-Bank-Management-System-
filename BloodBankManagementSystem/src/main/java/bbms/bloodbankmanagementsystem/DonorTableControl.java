package bbms.bloodbankmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class DonorTableControl {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField id;
    @FXML
    private TextField Name;
    @FXML
    private TextField bGroup;
    @FXML
    private TextField ContactNo;
    @FXML
    private TextField city;
    @FXML
    private TextField gender;
    @FXML
    private TextField Birhdate;
    @FXML
    private TextField lastdod;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_Button_Receiver;

    @FXML
    private Button Delete_button_Receiver;

    @FXML
    private TableView<donors> donors;

    @FXML
    private Button Update_Button_Receiver;

    @FXML
    private TableColumn<donors, String> donorName;

    @FXML
    private TableColumn<donors, Integer> donorId;

    @FXML
    private TableColumn<donors, String> bloodGroup;

    @FXML
    private TableColumn<donors, String> gender_t;
    @FXML
    private TableColumn<donors, String> city_t;

    @FXML
    private TableColumn<donors, String> Password;

    @FXML
    private TableColumn<donors, String> Email;
    @FXML
    private TableColumn<donors, Integer> Contact;
    @FXML
    private TableColumn<donors, Date> Birthdate;

    @FXML
    private TableColumn<donors, Date > Ldod;



    ObservableList<User> ListM;

    @FXML
    ResultSet rs = null;
    @FXML
    PreparedStatement pst = null;



    public void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*public void initialize(URL url,ResourceBundle resources){
        .setCellValueFactory(new PropertyValueFactory<>("donorID"));
        donorName.setCellValueFactory(new PropertyValueFactory<>("donorName"));
        bloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
        ReceiverId.setCellValueFactory(new PropertyValueFactory<>("receiverID"));
        ReceiverName.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        Amount_t.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        ListM = MySqlConnection.List;
        System.out.println(ListM.get(1).);
        donations.setItems(ListM);
    }

     */

}
