package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TotalDonationsControl extends MySqlConnection implements Initializable  {
    @FXML
    private TextField dId;
    @FXML
    private TextField dName;
    @FXML
    private TextField bGroup;
    @FXML
    private TextField rId;
    @FXML
    private TextField rName;
    @FXML
    private TextField amount;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_Button_Receiver;

    @FXML
    private Button Delete_button_Receiver;

    @FXML
    private TableView<donations> donations;

    @FXML
    private Button Update_Button_Receiver;

    @FXML
    private TableColumn<donations, String> donorName;

    @FXML
    private TableColumn<donations, String> donorId;

    @FXML
    private TableColumn<donations, String> bloodGroup;

    @FXML
    private TableColumn<donations, String> ReceiverId;
    @FXML
    private TableColumn<donations, String> ReceiverName;

    @FXML
    private TableColumn<donations, String> Amount;

    @FXML
    ObservableList<donations> ListM;

    @FXML
    ResultSet rs = null;
    @FXML
    PreparedStatement pst = null;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToDashboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void insert(ActionEvent event) throws IOException{
        Connection conn = MySqlConnection.ConnectDB();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql= "INSERT INTO donations(donorId,donorName,bloodGroup,ReceiverId,ReceiverName,Amount)VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
           preparedstatement.setString(1, dId.getText());

            preparedstatement.setString(2, dName.getText());
            preparedstatement.setString(3, bGroup.getText());
            preparedstatement.setString(4, rId.getText());
            preparedstatement.setString(5, rName.getText());
            preparedstatement.setString(6, amount.getText());
            preparedstatement.executeUpdate();

            System.out.println();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        donorId.setCellValueFactory(new PropertyValueFactory<>("DonorId"));
        donorName.setCellValueFactory(new PropertyValueFactory<>("donorname"));
        bloodGroup.setCellValueFactory(new PropertyValueFactory<>("bloodgroup"));
        ReceiverId.setCellValueFactory(new PropertyValueFactory<>("receiverid"));
        ReceiverName.setCellValueFactory(new PropertyValueFactory<>("receivername"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ListM = MySqlConnection.loadDatabase();
        donations.setItems(ListM);

    }



}
