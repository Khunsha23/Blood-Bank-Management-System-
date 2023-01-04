package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReportsControl {
    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane root1;
    private Parent root;
    AnchorPane reportAnchorPane;

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
    public void switchToDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
