package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ReceiverInfoControl extends loginreceivercontrol implements Initializable {
    @FXML
    private Label request;
    @FXML
    private Label BloodGroup;
static String BG;
    @FXML
    private Label ContactNum;
static String Number;
    @FXML
    private Label DateOfBirth;
static java.sql.Date Date;
    @FXML
    private Label Emailaddress;
static String email;
    @FXML
    private Label NameReceiver;
    static String Name;

    @FXML
    private Label ReceiverID;
    static String ID;
    private Stage stage;
    private Scene scene;
    private Parent root;


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

                FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("LocationsForUsers.fxml"));
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
        } catch (SQLException ex) {
            System.out.println(ex);
            Alert pending = new Alert(Alert.AlertType.INFORMATION,"You already have a pending request!", ButtonType.OK);
            pending.showAndWait();
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void ShowData() throws SQLException {
        Connection conn = MySqlConnection.ConnectDB();

        try {
            conn.createStatement().execute("SELECT * FROM receivers WHERE ContactNumber ="+contact);
            ReceiverID.setText(receiverID);
            ContactNum.setText(contact);
            DateOfBirth.setText(String.valueOf(dob));
            Emailaddress.setText(email);
            BloodGroup.setText(bg);
            NameReceiver.setText(nameDonor);
        }catch (Exception e){
            System.out.println(e);
        }
        conn.close();
    }
    public static void Information(String number){

        Connection conn = MySqlConnection.ConnectDB();
        String query = "SELECT * FROM receivers Where ContactNumber =";
        try {
            PreparedStatement preparedstatement = conn.prepareStatement(query + number);
            resultset = preparedstatement.executeQuery(query + number);
            while (resultset.next()){
                contact = resultset.getString("ContactNumber");
                password = resultset.getString("Password");
                bg = resultset.getString("BloodGroup");
                dob = resultset.getDate("birthday");
                email = resultset.getString("EmailAddress");
                nameDonor = resultset.getString(2);
                receiverID = resultset.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
