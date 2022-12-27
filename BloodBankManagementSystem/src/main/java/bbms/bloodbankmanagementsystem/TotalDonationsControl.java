package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    public TableView<donations> donations;

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
        refreshTable();
        stage.show();
    }
    @FXML
    public void ShowSelected(MouseEvent event){
        ListM = donations.getSelectionModel().getSelectedItems();

        if(ListM.size() == 0){

        }else {
            for(int i = 0; i < ListM.size(); i++){
                dId.setText(String.valueOf(ListM.get(0).DonorId));
                dName.setText(ListM.get(0).donorname);
                bGroup.setText(ListM.get(0).bloodgroup);
                rId.setText(ListM.get(0).receiverid);
                rName.setText(ListM.get(0).receivername);
                amount.setText(ListM.get(0).amount);
            }
        }
    }
    @FXML
    private void delete(ActionEvent event) throws IOException{

        Connection conn= ConnectDB();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql= "delete from donations where DonorId=?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            if(!rId.getText().equals("")){
                preparedstatement.setString(1, dId.getText());
                preparedstatement.executeUpdate();
                loadData();
                refreshTable();
                loadData();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void Update(ActionEvent event) throws IOException {
        Connection conn = ConnectDB();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "UPDATE donations SET DonorId=?,DonorName=?,BloodGroup=?,ReceiverId=?,ReceiverName=?,Amount=? WHERE donations.DonorId=?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            if(!rId.getText().equals("")){
                preparedstatement.setString(1, dId.getText());
                preparedstatement.setString(2, dName.getText());
                preparedstatement.setString(3, bGroup.getText());
                preparedstatement.setString(4, rId.getText());
                preparedstatement.setString(5, rName.getText());
                preparedstatement.setString(6, amount.getText());
                preparedstatement.setString(7, dId.getText());
                preparedstatement.executeUpdate();
                loadData();
                refreshTable();
                loadData();
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void insert(ActionEvent event) throws IOException {
        Connection conn = MySqlConnection.ConnectDB();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "INSERT INTO donations(donorId,donorName,bloodGroup,ReceiverId,ReceiverName,Amount)VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            preparedstatement.setString(1, dId.getText());
            preparedstatement.setString(2, dName.getText());
            preparedstatement.setString(3, bGroup.getText());
            preparedstatement.setString(4, rId.getText());
            preparedstatement.setString(5, rName.getText());
            preparedstatement.setString(6, amount.getText());
            preparedstatement.executeUpdate();
            loadData();
            refreshTable();
            loadData();
            System.out.println();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public void loadData(){
        donorId.setCellValueFactory(new PropertyValueFactory<>("DonorId"));
        donorName.setCellValueFactory(new PropertyValueFactory<>("donorname"));
        bloodGroup.setCellValueFactory(new PropertyValueFactory<>("bloodgroup"));
        ReceiverId.setCellValueFactory(new PropertyValueFactory<>("receiverid"));
        ReceiverName.setCellValueFactory(new PropertyValueFactory<>("receivername"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ListM = MySqlConnection.loadDatabase();
        donations.setItems(ListM);
    }

    public void refreshTable(){
      ListM.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle resources) {
       loadData();
    }




}
