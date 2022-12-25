package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashBoardControl {
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
    public void switchToDonorsTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DonorsTableForAdminPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToReceiversTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ReceiverTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToRequestTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RequestsTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTotalDonations(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TotalDonations.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTotalStock(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("InventoryPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTotalReport(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLocation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BankLocations.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
