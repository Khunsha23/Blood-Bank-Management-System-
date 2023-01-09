package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminDashBoardControl {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToDonorsTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DonorsTableForAdminPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToReceiversTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ReceiverTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToRequestTable(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RequestsTable.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToTotalDonations(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TotalDonations.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToTotalStock(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("InventoryPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void print(ActionEvent actionEvent) throws JRException {

        String sourceFileName = "E:\\Exam\\Blood-Bank-Management-System-\\BloodBankManagementSystem\\src\\main\\resources\\Reports\\r.jrxml";
        JasperReport jasperReport = JasperCompileManager.compileReport(sourceFileName);
        Map<String, Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, MySqlConnection.ConnectDB());
        JasperViewer jasperViewer = new JasperViewer(jasperPrint);
        jasperViewer.setTitle("Total Donations");
        jasperViewer.setVisible(true);

    }
    @FXML
    public void switchToTotalReport(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void switchToLocation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("BankLocations.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
