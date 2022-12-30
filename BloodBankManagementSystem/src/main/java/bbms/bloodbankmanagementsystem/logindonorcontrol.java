package bbms.bloodbankmanagementsystem;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class logindonorcontrol extends signupValidations implements Validation{
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label BirthdayErr;
    @FXML
    private Label CrePassErr;
    @FXML
    private TextField CreatePassword;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField EmailAddress;
    @FXML
    private Label EmailErr;
    @FXML
    private TextField FullName;
    @FXML
    private TextField MobileNum;
    @FXML
    private TextField ReEnterPassword;
    @FXML
    private Label RePassErr;
    @FXML
    private Label errormsgNm;
    @FXML
    private Label numErr;
    @FXML
    private TextField MobileNumberTextField;
    @FXML
    private Label loginmsg;
    @FXML
    static Date dob;
    @FXML
    static String email;
    @FXML
    static String nameDonor;
    @FXML
    private PasswordField passwordField;
   public ObservableList<String> list3 = FXCollections.observableArrayList("Lahore","Karachi","Islamabad");
    @FXML
    private ComboBox<String> citybox;
    @FXML
    private Label BloodErr;
    @FXML
    private Label GenderErr;
    @FXML
    private Label cityErr;
    @FXML
    private ComboBox<String> BGComboBox;
    public void switchToDonorinfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    @Override
    public void SignupValidation(ActionEvent event) throws IOException {
        errormsgNm.setText("");
        numErr.setText("");
        CrePassErr.setText("");
        EmailErr.setText("");
        RePassErr.setText("");
        BloodErr.setText("");
        GenderErr.setText("");
        cityErr.setText("");
        BirthdayErr.setText("");

        if (FullName.getText().equals("")) {
            errormsgNm.setText("Empty Field");
        }else if (nameValidations(FullName.getText())) {
            errormsgNm.setText("Invalid format");
        }

        if (MobileNum.getText().equals("")){
            numErr.setText("Empty Field");
        }else if(ContactValidation(MobileNum.getText())) {
            numErr.setText("Invalid format");
        }
        if (CreatePassword.getText().isEmpty()) {
            CrePassErr.setText("Empty Field");
        }else if (passwordValidation(CreatePassword.getText())){
            CrePassErr.setText("At least 8 characters without space");
        }
        if (EmailAddress.getText().isEmpty()) {
            EmailErr.setText("Empty Field");
        }else if (EmailValidation(EmailAddress.getText())){
            EmailErr.setText("Invalid format");
        }
        if (!(ReEnterPassword.getText().equals(CreatePassword.getText()))){
            RePassErr.setText("Enter Same Password");
        }

        try {
            if (citybox.getValue()==null){
                cityErr.setText("Please Select City");
            }
            if (BGComboBox.getValue()==null){
                BloodErr.setText("Please Select Blood");
            }
            if (getGender()==null){
                GenderErr.setText("Please Select Gender");
            }
            if (dateOfBirth.getValue() == null){
                BirthdayErr.setText("Please Select Birthday");
            }
            System.out.println(getGender());
            System.out.println(BGComboBox.getValue());
            System.out.println(dateOfBirth.getValue());

        }catch (Exception e){

            System.out.println(e);
        }

        if (!nameValidations(FullName.getText())) {
            System.out.println("1");
            if (!ContactValidation(MobileNum.getText())) {
                System.out.println("2");
                if (!EmailValidation(EmailAddress.getText())) {
                    System.out.println("3");
                    if (!passwordValidation(CreatePassword.getText())) {
                        System.out.println("4");
                        if (ReEnterPassword.getText().equals(CreatePassword.getText())) {
                            System.out.println("5");
                            if (!(getGender().equals(null))) {
                                System.out.println("6");
                                if (!(BGComboBox.getValue().isEmpty())) {
                                    System.out.println("7");
                                    if (!String.valueOf(dateOfBirth.getValue()).isEmpty()) {
                                        System.out.println("8");
                                        Connection conn = MySqlConnection.ConnectDB();
                                        try {
                                            System.out.println("9");
                                            System.out.println(BGComboBox.getValue());
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                            String sql = "INSERT INTO donors(FullName,ContactNumber,EmailAddress,BloodGroup,City,Gender,birthday,Password)VALUES(?,?,?,?,?,?,?,?)";
                                            PreparedStatement preparedstatement = conn.prepareStatement(sql);
                                            preparedstatement.setString(1, FullName.getText());
                                            preparedstatement.setString(2, MobileNum.getText());
                                            preparedstatement.setString(3, EmailAddress.getText());
                                            preparedstatement.setString(4, BGComboBox.getValue());
                                            preparedstatement.setString(5, citybox.getValue());
                                            preparedstatement.setString(6, getGender());
                                            preparedstatement.setString(7, String.valueOf(dateOfBirth.getValue()));
                                            preparedstatement.setString(8, ReEnterPassword.getText());
                                            preparedstatement.executeUpdate();
                                            try {
                                                PreparedStatement Pst = conn.prepareStatement(query + MobileNum.getText());
                                                this.resultset = Pst.executeQuery(query + MobileNum.getText());
                                                while (resultset.next()){
                                                    mobileNumber = resultset.getString("ContactNumber");
                                                    password = resultset.getString("Password");
                                                    bg = resultset.getString("BloodGroup");
                                                    dob = resultset.getDate("birthday");
                                                    email = resultset.getString("EmailAddress");
                                                    nameDonor = resultset.getString(2);
                                                    donorid = resultset.getString(1);
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }
                                            System.out.println("Hello Im Here");
                                            root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
                                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                            scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.show();
                                        } catch (SQLException | ClassNotFoundException e) {
                                            numErr.setText("This number Already Exists");
                                            System.out.println(e);
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ShowCities(MouseEvent event) {
        citybox.setItems(list3);
    }
    public void switchToDonorSignup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    static String bg;
    ResultSet resultset;
    public static String mobileNumber;
    static String query = "SELECT * FROM donors Where ContactNumber =";
    public String password;
    public static String donorid;
    @Override
    public void loginValidation(ActionEvent event) {
        Connection conn = MySqlConnection.ConnectDB();
        try {
            PreparedStatement preparedstatement = conn.prepareStatement(query + MobileNumberTextField.getText());
            this.resultset = preparedstatement.executeQuery(query + MobileNumberTextField.getText());
            while (resultset.next()){
                mobileNumber = resultset.getString("ContactNumber");
                password = resultset.getString("Password");
                bg = resultset.getString("BloodGroup");
                dob = resultset.getDate("birthday");
                email = resultset.getString("EmailAddress");
                nameDonor = resultset.getString(2);
                donorid = resultset.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (MobileNumberTextField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            loginmsg.setText("Please Enter Username and Password");
        } else if (MobileNumberTextField.getText().isEmpty()) {
            loginmsg.setText("Enter mobile number");
        } else if (passwordField.getText().isEmpty()) {
            loginmsg.setText("Enter Password");
        } else if (MobileNumberTextField.getText().equals(mobileNumber) && passwordField.getText().equals(password)) {
            loginmsg.setStyle("-fx-text-fill:Green;");
            loginmsg.setText("Logged in Successfully");
            try {
                root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                System.out.println(e);
            }

        } else{
            loginmsg.setText("Invalid Credentials");
        }
    }
    String getGender()
    {
        if(MaleCheck.isSelected())
        {
            return "Male";
        }
        else if(FemaleCheck.isSelected())
        {
            return "Female";
        }
        else
        {
            return null;
        }
    }
    @FXML
    private JFXRadioButton FemaleCheck;
    @FXML
    private JFXRadioButton MaleCheck;
    public void fem() {
        if (FemaleCheck.isSelected()) {
            MaleCheck.setSelected(false);
        }
    }
    public void male() {
        if (MaleCheck.isSelected()) {
            FemaleCheck.setSelected(false);
        }
    }
}