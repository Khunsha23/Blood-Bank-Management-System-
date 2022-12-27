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
import java.util.ResourceBundle;

public class RequestTableControl implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<receivers> requests;
    @FXML
    private TableColumn<donors, String> name;

    @FXML
    private TableColumn<donors, Integer> id;

    @FXML
    private TableColumn<donors, String> bloodGroup;

    @FXML
    private TableColumn<donors, String> city_t;
    @FXML
    private TableColumn<donors, String> Contact;

    public void switchToDashBoard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<receivers> ListM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            Contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            city_t.setCellValueFactory(new PropertyValueFactory<>("City"));
            bloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
            ListM = MySqlConnection.getRequest();
            requests.setItems(ListM);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
