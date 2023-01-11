package bbms.bloodbankmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class signupreceivercontrol extends signupValidations implements Validation{
    @FXML
    private ComboBox<String> BGComboBox1;

    @FXML
    private Label BirthdayErr1;

    @FXML
    private Label BloodErr1;

    @FXML
    private Label CrePassErr1;

    @FXML
    private JFXButton CreateAccountBtn;

    @FXML
    private TextField CreatePassword1;

    @FXML
    private TextField EmailAddress1;

    @FXML
    private Label EmailErr1;

    @FXML
    private JFXRadioButton FemaleCheck1;

    @FXML
    private TextField FullName1;

    @FXML
    private Label GenderErr1;

    @FXML
    private JFXRadioButton MaleCheck1;

    @FXML
    private TextField MobileNum1;

    @FXML
    private TextField ReEnterPassword1;

    @FXML
    private Label RePassErr1;

    @FXML
    private Label cityErr1;

    @FXML
    private ComboBox<String> citybox1;

    @FXML
    private DatePicker dateOfBirth1;

    @FXML
    private Label errormsgNm1;

    @FXML
    private Label numErr1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToHome(ActionEvent event) throws IOException {
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
    public void switchToReceiverinfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Receiverinfo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void loginValidation(ActionEvent event) throws IOException, SQLException {

    }
    static String bg;
    ResultSet resultset;
    public static String mobileNumber;
    static String query = "SELECT * FROM receivers Where ContactNumber =";

    public ObservableList<String> list3 = FXCollections.observableArrayList("Lahore","Karachi","Islamabad");
    @FXML
    void ShowCities(MouseEvent event) {
        citybox1.setItems(list3);
    }
    @FXML
    @Override
    public void SignupValidation(ActionEvent event) throws IOException {
        errormsgNm1.setText("");
        numErr1.setText("");
        CrePassErr1.setText("");
        EmailErr1.setText("");
        RePassErr1.setText("");
        BloodErr1.setText("");
        GenderErr1.setText("");
        cityErr1.setText("");
        BirthdayErr1.setText("");

        if (FullName1.getText().equals("")) {
            errormsgNm1.setText("Empty Field");
        }else if (nameValidations(FullName1.getText())) {
            errormsgNm1.setText("Invalid format");
        }

        if (MobileNum1.getText().equals("")){
            numErr1.setText("Empty Field");
        }else if(ContactValidation(MobileNum1.getText())) {
            numErr1.setText("Invalid format");
        }
        if (CreatePassword1.getText().isEmpty()) {
            CrePassErr1.setText("Empty Field");
        }else if (passwordValidation(CreatePassword1.getText())){
            CrePassErr1.setText("At least 8 characters without space");
        }
        if (EmailAddress1.getText().isEmpty()) {
            EmailErr1.setText("Empty Field");
        }else if (EmailValidation(EmailAddress1.getText())){
            EmailErr1.setText("Invalid format");
        }
        if (!(ReEnterPassword1.getText().equals(CreatePassword1.getText()))){
            RePassErr1.setText("Enter Same Password");
        }

        try {
            if (citybox1.getValue()==null){
                cityErr1.setText("Please Select City");
            }
            if (BGComboBox1.getValue()==null){
                BloodErr1.setText("Please Select Blood");
            }
            if (getGender()==null){
                GenderErr1.setText("Please Select Gender");
            }
            if (dateOfBirth1.getValue() == null){
                BirthdayErr1.setText("Please Select Birthday");
            }



            System.out.println(BGComboBox1.getValue());
            System.out.println(dateOfBirth1.getValue());

        }catch (Exception e){

            System.out.println(e);
        }

        if (!nameValidations(FullName1.getText())) {
            System.out.println("1");
            if (!ContactValidation(MobileNum1.getText())) {
                System.out.println("2");
                if (!EmailValidation(EmailAddress1.getText())) {
                    System.out.println("3");
                    if (!passwordValidation(CreatePassword1.getText())) {
                        System.out.println("4");
                        if (ReEnterPassword1.getText().equals(CreatePassword1.getText())) {
                            System.out.println("5");
                            if (getGender() != null) {
                                System.out.println("6");
                                if (!(BGComboBox1.getValue().isEmpty())) {
                                    System.out.println("7");
                                    if (!String.valueOf(dateOfBirth1.getValue()).isEmpty()) {
                                        LocalDate dateOfBirth = dateOfBirth1.getValue();
                                        LocalDate currentDate = LocalDate.now();
                                        Period age = Period.between(dateOfBirth, currentDate);
                                        int years = age.getYears();
                                        if(years>=18) {
                                            System.out.println("8");
                                            Connection conn = MySqlConnection.ConnectDB();
                                            try {

                                                MySqlConnection.InsertReceiver(FullName1.getText(), MobileNum1.getText(),
                                                        EmailAddress1.getText(), BGComboBox1.getValue(), ReEnterPassword1.getText(),
                                                        citybox1.getValue(), getGender(), Date.valueOf(dateOfBirth1.getValue()));
                                                try {
                                                    PreparedStatement Pst = conn.prepareStatement(query + MobileNum1.getText());
                                                    this.resultset = Pst.executeQuery(query + MobileNum1.getText());
                                                    while (resultset.next()) {
                                                        mobileNumber = resultset.getString("ContactNumber");
                                                        password = resultset.getString("Password");
                                                        bg = resultset.getString("BloodGroup");
                                                        dob = resultset.getDate("birthday");
                                                        email = resultset.getString("EmailAddress");
                                                        Name = resultset.getString(2);
                                                        receiverId = resultset.getString(1);
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(e);
                                                }
                                                ReceiverInfoControl.Information(mobileNumber);
                                                FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("Receiverinfo.fxml"));
                                                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                                                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                                                if (stage.isMaximized()) {
                                                    scene = new Scene(fxmlLoader1.load(), screenSize.getWidth(), screenSize.getHeight());
                                                } else {
                                                    scene = new Scene(fxmlLoader1.load());
                                                }
                                                stage.setScene(scene);
                                                stage.show();
                                            } catch (SQLException e) {
                                                numErr1.setText("This number Already Exists");
                                                System.out.println(e);
                                                throw new RuntimeException(e);
                                            }
                                        }else {
                                            Alert pending = new Alert(Alert.AlertType.INFORMATION, "your age is under 18 you cannot register", ButtonType.OK);
                                            pending.showAndWait();
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
    static String password;
    static Date dob;
    static String email;
    static String receiverId;
    static String Name;

    String getGender()
    {
        if(MaleCheck1.isSelected())
        {
            return "Male";
        }
        else if(FemaleCheck1.isSelected())
        {
            return "Female";
        }
        else
        {
            return null;
        }
    }
}
