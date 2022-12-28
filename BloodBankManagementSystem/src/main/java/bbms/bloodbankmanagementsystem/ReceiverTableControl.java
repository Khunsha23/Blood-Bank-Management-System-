package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ReceiverTableControl implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<receivers> receivers;
    @FXML
    private TableColumn<donors, String> recName;

    @FXML
    private TableColumn<donors, Integer> recId;

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
    private TableColumn<donors, String> Contact;
    @FXML
    private TableColumn<donors, Date> Birthdate;
    ObservableList<receivers> ListM;



    public void switchToHome(ActionEvent event) throws IOException, SQLException {

        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        refresh();
        stage.show();
    }
    public void refresh(){
        ListM.clear();
    }

    private void ShowData() throws SQLException {
        try {
            System.out.println("Hello");
            recId.setCellValueFactory(new PropertyValueFactory<>("name"));
            recName.setCellValueFactory(new PropertyValueFactory<>("ID"));
            bloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
            gender_t.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            city_t.setCellValueFactory(new PropertyValueFactory<>("City"));
            Password.setCellValueFactory(new PropertyValueFactory<>("password"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            Contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            Birthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            ListM = MySqlConnection.getReceiver();
            receivers.setItems(ListM);
        }catch (Exception e){
            System.out.println(e);
        }

    }


    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ShowData();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
