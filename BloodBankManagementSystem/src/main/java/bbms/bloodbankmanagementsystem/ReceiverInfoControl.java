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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void search(ActionEvent event) throws SQLException {
        Connection conn = MySqlConnection.ConnectDB();
        String query;
        String a=null;
        String i = null;
        String f= null;
        String cn= null;
        String c= null;
        String bg= null;
        query = "SELECT Amount FROM inventory WHERE BloodGroup = '"+super.bg+"'";
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet output = stm.executeQuery();

            while(output.next()){
                a= output.getString("Amount");

            }
            if(a.equals("0")){
                System.out.println(receiverID);
                String sql= "SELECT IdNumber,FullName,ContactNumber,City,BloodGroup FROM receivers WHERE IdNumber ="+receiverID;
                PreparedStatement stm1 = conn.prepareStatement(sql);
                ResultSet output1 = stm1.executeQuery(sql);
                System.out.println("A");
                while (output1.next()){
                    System.out.println("B");
                    i= output1.getString("IdNumber");
                    f= output1.getString("FullName");
                    cn= output1.getString("ContactNumber");
                    c= output1.getString("City");
                    bg= output1.getString("BloodGroup");
                }
                String sql2 = "INSERT INTO requests(ReceiverId,FullName,ContactNumber,City,BloodGroup)VALUES(?,?,?,?,?)";
                PreparedStatement preparedstatement = conn.prepareStatement(sql2);
                preparedstatement.setString(1, i);
                preparedstatement.setString(2, f);
                preparedstatement.setString(3, cn);
                preparedstatement.setString(4, c);
                preparedstatement.setString(5, bg);
                preparedstatement.executeUpdate();

            }
            if(!(a.equals("0"))){
                root = FXMLLoader.load(getClass().getResource("BankLocations.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void ShowData() throws SQLException {
        Connection conn = MySqlConnection.ConnectDB();

        try {
            conn.createStatement().execute("SELECT * FROM receivers WHERE ContactNumber ="+contact);
            ReceiverId.setText(receiverID);
            Contact.setText(contact);
            dob.setText(String.valueOf(loginreceivercontrol.dob));
            email.setText(loginreceivercontrol.email);
            BloodGroup.setText(bg);
            receiverName.setText(nameDonor);
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
