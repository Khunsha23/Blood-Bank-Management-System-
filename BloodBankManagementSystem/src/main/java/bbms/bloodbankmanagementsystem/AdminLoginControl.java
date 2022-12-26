package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginControl implements Validation{

    private Parent text;
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
    public void switchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button LoginBtn;

    @FXML
    private TextField MobileNumberTextField;

    @FXML
    private Label loginMsg;

    @FXML
    private PasswordField passwordField;

    final String mobileNumber = "03436299271";
    final String password = "dsaqw_1620";


    @Override
    public void loginValidation(ActionEvent event) throws IOException {

        if(MobileNumberTextField.getText().isEmpty()&& passwordField.getText().isEmpty()){
            loginMsg.setText("Please Enter Username and Password");
        }
        else if(MobileNumberTextField.getText().isEmpty()){
            loginMsg.setText("Enter mobile number");
        }else if (passwordField.getText().isEmpty()){
            loginMsg.setText("Enter Password");
        } else if (MobileNumberTextField.getText().equals(mobileNumber)&& passwordField.getText().equals(password)) {


            root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            loginMsg.setText("Invalid Credentials");
        }
    }

    @Override
    public void SignupValidation(ActionEvent event) {

    }
}
