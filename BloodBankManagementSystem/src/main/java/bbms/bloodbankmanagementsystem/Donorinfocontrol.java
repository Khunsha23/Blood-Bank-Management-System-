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
    private Label Emailaddress;
    @FXML
    private Label NameDonor1;
    @FXML
    private Label DonorID;

    private void ShowData() throws SQLException {
        Connection conn = MySqlConnection.ConnectDB();

        try {
            conn.createStatement().execute("SELECT * FROM donors WHERE ContactNumber ="+ mobileNumber);
            System.out.println(donorid);
            DonorID.setText(donorid);
            ContactNum.setText(mobileNumber);
            DateOfBirth.setText(String.valueOf(dob));
            Emailaddress.setText(email);
            BloodGroup.setText(bg);
            NameDonor1.setText(nameDonor);


        }catch (Exception e){
            System.out.println(e);
        }
               conn.close();
    }
    @FXML
    void switchToLocations(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LocationsForUsers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
