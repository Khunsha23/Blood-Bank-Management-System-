package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminInventoryControl implements Initializable {

    private Stage stage;
    private Scene scene;
    @FXML
    private Label Apos;
    @FXML
    private Label Aneg;
    @FXML
    private Label Bpos;
    @FXML
    private Label Bneg;
    @FXML
    private Label ABpos;
    @FXML
    private Label ABneg;
    @FXML
    private Label Opos;
    @FXML
    private Label Oneg;


    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn= MySqlConnection.ConnectDB();
        Label[] array = new Label[]{Apos,Aneg,ABpos,ABneg,Bpos,Bneg,Opos,Oneg};
        try{

            String sql= "SELECT Amount From inventory";
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            ResultSet output = preparedStatement.executeQuery(sql);
            for (int i = 0; i < 8 ; i++) {
                output.next();
                array[i].setText(output.getString(1));
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
