package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class inventorycontrol implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField amount;
    @FXML
    private ComboBox<String> BloodGroups1;

    public void switchToStartPage(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("startpage.fxml"));
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
        public void switchToHome(ActionEvent event) throws IOException {

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
    @FXML
    public void addstock(ActionEvent event) throws IOException {

        Label[] Labels = {Apos,Aneg,ABpos,ABneg,Bpos,Bneg,Opos,Oneg};
        for (Label label : Labels) {
            label.setText("");
        }
        Connection conn = MySqlConnection.ConnectDB();
        String query;
        int Amount= Integer.parseInt(amount.getText());
        int a;
        Integer newAmount;
        String newA= null;
        System.out.println(BloodGroups1.getValue());
        query = "SELECT Amount FROM inventory WHERE BloodGroup = '" + BloodGroups1.getValue() + "'";

        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet output = stm.executeQuery();
            while (output.next()) {
                a = Integer.parseInt(output.getString("Amount"));
                newAmount= Amount+a;
                newA= String.valueOf(newAmount);
                System.out.println(newAmount);
            }
            String sql = "UPDATE inventory SET Amount=? WHERE BloodGroup = ?";
            PreparedStatement stm1 = conn.prepareStatement(sql);
                // Set the values for the 'Amount' and 'BloodGroup' fields
                stm1.setString(1, newA);
                stm1.setString(2, BloodGroups1.getValue());
                // Execute the update statement
                stm1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        showInventory();
    }


    @FXML
    private Label ABneg;

    @FXML
    private Label ABpos;

    @FXML
    private Label Aneg;

    @FXML
    private Label Apos;


    @FXML
    private Label Bneg;

    @FXML
    private Label Bpos;

    @FXML
    private Label Oneg;

    @FXML
    private Label Opos;

    @FXML
    public void showInventory(){
        Label[] Labels = {Apos,Aneg,ABpos,ABneg,Bpos,Bneg,Opos,Oneg};

        ArrayList<Stock> stockList;
        stockList = MySqlConnection.getStock();
        int i = 0;
        for (Stock stock : stockList) {
            Labels[i].setText(String.valueOf(stock.amount));
            i++;
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showInventory();
    }
}
