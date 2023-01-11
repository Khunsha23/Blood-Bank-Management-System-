package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginControl implements Validation{

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("BankLocations.fxml"));
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
    private TextField MobileNumberTextField;

    @FXML
    private Label loginMsg;

    @FXML
    private PasswordField passwordField;

    final String mobileNumber = "98";
    final String password = "abc";


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
        }else{
            loginMsg.setText("Invalid Credentials");
        }
    }

    @Override
    public void SignupValidation(ActionEvent event) {

    }
}
