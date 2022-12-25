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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class logindonorcontrol implements Validation {

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
    public void switchToDonorSignup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToDonorInfo(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField MobileNumberTextField;
    @FXML
    private Label loginmsg;

    @FXML
    private PasswordField passwordField;


    String mobileNumber;

    String password;
    @Override
    public void loginValidation(ActionEvent event) throws IOException {

        Connection conn = MySqlConnection.ConnectDB();

        try{
            PreparedStatement preparedstatement = conn.prepareStatement("SELECT * FROM donors Where ContactNumber ="+MobileNumberTextField.getText());
            ResultSet resultset = preparedstatement.executeQuery();
            while (resultset.next()){

                mobileNumber = resultset.getString("ContactNumber");
                password = resultset.getString("Password");


            }
        }catch (Exception e){
            System.out.println(e);
        }

        if(MobileNumberTextField.getText().isEmpty()&& passwordField.getText().isEmpty()){
            loginmsg.setText("Please Enter Username and Password");
        }
        else if(MobileNumberTextField.getText().isEmpty()){
            loginmsg.setText("Enter mobile number");
        }else if (passwordField.getText().isEmpty()){
            loginmsg.setText("Enter Password");
        } else if (MobileNumberTextField.getText().equals(mobileNumber)&& passwordField.getText().equals(password)) {

            loginmsg.setStyle("-fx-background-color:green;");
            loginmsg.setText("Logged in Successfully");
            root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
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

