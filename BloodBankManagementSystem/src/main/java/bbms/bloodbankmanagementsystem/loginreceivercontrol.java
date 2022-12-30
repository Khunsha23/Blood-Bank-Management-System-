package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class loginreceivercontrol implements Validation {
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

    public void switchToReceiverSignup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUpReceiver.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField MobileNumberTextField;
    @FXML
    private Label loginmsg;
    static String contact;
    static Date dob;
    static String email;

    static String nameDonor;
    static String bg;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label BloodGroup;

    @FXML
    private Label Contact;

    @FXML
    private Label ReceiverId;

    @FXML
    private Label DOB;

    @FXML
    private Label eligibility;

    @FXML
    private Label Email;

    @FXML
    private Label receiverName;
    ResultSet resultset;

    static String receiverID;
     String password ;
    @Override
    public void loginValidation(ActionEvent event) throws IOException {
        Connection conn = MySqlConnection.ConnectDB();
        String query = "SELECT * FROM receivers Where ContactNumber =";
        try {
            PreparedStatement preparedstatement = conn.prepareStatement(query + MobileNumberTextField.getText());
            this.resultset = preparedstatement.executeQuery(query + MobileNumberTextField.getText());
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

        if(MobileNumberTextField.getText().isEmpty()&& passwordField.getText().isEmpty()){
            loginmsg.setText("Please Enter Username and Password");
        }
        else if(MobileNumberTextField.getText().isEmpty()){
            loginmsg.setText("Enter mobile number");
        }else if (passwordField.getText().isEmpty()){
            loginmsg.setText("Enter Password");
        } else if (MobileNumberTextField.getText().equals(contact)&& passwordField.getText().equals(password)) {
            loginmsg.setStyle("-fx-text-color:green;");
            loginmsg.setText("Logged in Successfully");
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Receiverinfo.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            loginmsg.setText("Invalid Credentials");
        }
    }

    @Override
    public void SignupValidation(ActionEvent event) {


    }


}
