package bbms.bloodbankmanagementsystem;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class logindonorcontrol extends combox implements Validation {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField MobileNumberTextField;
    @FXML
    private Label loginmsg;
    static String contact;
    static  Date dob;
    static String email;
    static Date lastVisited;
    static String nameDonor;
    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> citybox;
    @FXML
    private ComboBox<?> BGComboBox;
    @FXML
    private JFXRadioButton FemaleCheck;

    @FXML
    private JFXRadioButton MaleCheck;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Lahore","Karachi","Islamabad");
        citybox.setItems(list);
    }

    public void fem(){
        if(FemaleCheck.isSelected()){
            MaleCheck.setSelected(false);
        }
    }
    public void male(){
        if(MaleCheck.isSelected()){
            FemaleCheck.setSelected(false);
        }
    }

    public void switchToDonorinfo(ActionEvent event) throws IOException {
        String city;
        String bg;
        city=citybox.getValue();
        bg= (String) BGComboBox.getValue();
        root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void SignupValidation(ActionEvent event) {

    }

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


    static String bg;

    @FXML
    public void switchToDonorInfo(ActionEvent event) throws IOException, SQLException {

        Connection conn = MySqlConnection.ConnectDB();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM donors ");
        ResultSet rs = pst.executeQuery();

        root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ResultSet resultset;

    public static String mobileNumber;
    static String query= "SELECT * FROM donors Where ContactNumber =";

    public String password;
    @Override
    public void loginValidation(ActionEvent event) throws IOException, SQLException {


        Connection conn = MySqlConnection.ConnectDB();
        try{
            PreparedStatement preparedstatement = conn.prepareStatement(query+MobileNumberTextField.getText());
             this.resultset = preparedstatement.executeQuery(query+MobileNumberTextField.getText());
            while (resultset.next()){

                this.mobileNumber = resultset.getString("ContactNumber");
                this.password = resultset.getString("Password");
                this.bg = resultset.getString("BloodGroup");
               // this.contact = resultset.getString("ContactNumber");
                this.dob = resultset.getDate("birthday");
                this.email = resultset.getString("EmailAddress");
                this.lastVisited = resultset.getDate("LastDateofDonation");
                this.nameDonor = resultset.getString(2);


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

            loginmsg.setStyle("-fx-text-fill:Green;");
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
}

