package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminInventoryControl {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField Apos;
    @FXML
    private TextField Aneg;
    @FXML
    private TextField Bpos;
    @FXML
    private TextField Bneg;
    @FXML
    private TextField ABpos;
    @FXML
    private TextField ABneg;
    @FXML
    private TextField Opos;
    @FXML
    private TextField Oneg;

    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
