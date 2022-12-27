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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RequestTableControl implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField CNo;
    @FXML
    private TextField bGroup;
    @FXML
    private TextField rId;
    @FXML
    private TextField rName;
    @FXML
    private TextField city;
    @FXML
    private TableView<receivers> requests;
    @FXML
    private TableColumn<receivers, String> name;

    @FXML
    private TableColumn<receivers, Integer> id;

    @FXML
    private TableColumn<receivers, String> bloodGroup;

    @FXML
    private TableColumn<receivers, String> city_t;
    @FXML
    private TableColumn<receivers, String> Contact;

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
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
    public void insert(ActionEvent event) throws IOException {
        Connection conn = MySqlConnection.ConnectDB();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "INSERT INTO requests(id,name,Contact,city_t,bloodGroup)VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            preparedstatement.setString(1, rId.getText());

            preparedstatement.setString(2, rName.getText());
            preparedstatement.setString(3, CNo.getText());
            preparedstatement.setString(4, city.getText());
            preparedstatement.setString(5, bGroup.getText());
            preparedstatement.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
}
