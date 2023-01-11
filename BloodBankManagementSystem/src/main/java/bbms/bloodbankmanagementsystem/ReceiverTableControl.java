package bbms.bloodbankmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ReceiverTableControl implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<User> receivers;
    @FXML
    private TableColumn<User, String> recName;

    @FXML
    private TableColumn<User, Integer> recId;

    @FXML
    private TableColumn<User, String> bloodGroup;

    @FXML
    private TableColumn<User, String> gender_t;
    @FXML
    private TableColumn<User, String> city_t;

    @FXML
    private TableColumn<User, String> Password;

    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, String> Contact;
    @FXML
    private TableColumn<receivers, Date> Birthdate;
    ObservableList<User> ListM;



    public void switchToHome(ActionEvent event) throws IOException, SQLException {


        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("AdminDashboard.fxml"));
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
    public void refresh(){
        ListM.clear();
    }

    private void ShowData() throws SQLException {
        try {
             recId.setCellValueFactory(new PropertyValueFactory<>("name"));
            recName.setCellValueFactory(new PropertyValueFactory<>("ID"));
            bloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
            gender_t.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            city_t.setCellValueFactory(new PropertyValueFactory<>("City"));
            Password.setCellValueFactory(new PropertyValueFactory<>("password"));
            Email.setCellValueFactory(new PropertyValueFactory<>("email"));
            Contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            Birthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            ListM = MySqlConnection.GenericList();
        } catch (Exception e) {
            System.out.println(e);
        }
        ObservableList<User> users = FXCollections.observableArrayList();
        for (User Data : ListM) {
            if (Data instanceof receivers) {
                users.add(Data);
            }

        }
        receivers.setItems(users);

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
