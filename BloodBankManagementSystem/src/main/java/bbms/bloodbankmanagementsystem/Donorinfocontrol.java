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

public class Donorinfocontrol extends logindonorcontrol implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label BloodGroup;

    @FXML
    private Label ContactNum;

    @FXML
    private Label DateOfBirth;

    @FXML
    private Label Email;

    @FXML
    private Label LastVisited;

    @FXML
    private Label NameDonor1;

    @FXML
    private Label ReceiverID;

    @FXML
    private Label StatusEligibility;

    private void ShowData() throws SQLException {
        Connection conn = MySqlConnection.ConnectDB();

        try {
            conn.createStatement().execute("SELECT * FROM donors WHERE ContactNumber ="+super.mobileNumber);
            ReceiverID.setText("I don't know yet!");
            ContactNum.setText(super.mobileNumber);
            DateOfBirth.setText(String.valueOf(super.dob));
            LastVisited.setText(String.valueOf(super.lastVisited));
            Email.setText(super.email);
            BloodGroup.setText(super.bg);
            NameDonor1.setText(super.nameDonor);
            StatusEligibility.setText("You can visit anytime");
        }catch (Exception e){
            System.out.println(e);
        }
               conn.close();
    }


    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            ShowData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
