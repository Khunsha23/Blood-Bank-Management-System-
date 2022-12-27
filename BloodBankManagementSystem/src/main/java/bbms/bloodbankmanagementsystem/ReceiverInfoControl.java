package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReceiverInfoControl extends loginreceivercontrol implements Initializable {
    @FXML
    private Label request;
    @FXML
    private Label BloodGroup;

    @FXML
    private Label Contact;

    @FXML
    private Label LastVisited;

    @FXML
    private Label ReceiverId;

    @FXML
    private Label dob;

    @FXML
    private Label eligibility;

    @FXML
    private Label email;

    @FXML
    private Label receiverName;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void requestbutton(ActionEvent event){
        request.setText("Your request has been received!");

    }

    private void ShowData() throws SQLException {
        Connection conn = MySqlConnection.ConnectDB();

        try {
            conn.createStatement().execute("SELECT * FROM receivers WHERE ContactNumber ="+super.mobileNumber);
            ReceiverId.setText("I don't know yet!");
            Contact.setText(super.mobileNumber);
            dob.setText(String.valueOf(super.dob));
            LastVisited.setText(String.valueOf(super.lastVisited));
            email.setText(super.email);
            BloodGroup.setText(super.bg);
            receiverName.setText(super.nameDonor);
            eligibility.setText("You can visit anytime");
        }catch (Exception e){
            System.out.println(e);
        }
        conn.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ShowData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
