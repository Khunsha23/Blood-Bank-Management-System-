package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
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
    private ComboBox<String> bGroup;
    @FXML
    private TextField rId;
    @FXML
    private TextField rName;
    @FXML
    private TextField amount;
    @FXML
    public TableView<donations> donations;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToDashboard(ActionEvent event) throws IOException {
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
    }
    TextField[] textFields;
    TextField[] numberFields;

    boolean FormatCheck(){
        boolean formatCheck =true;
        textFields = new TextField[]{dName,rName};
        numberFields = new TextField[]{dId,rId,amount};
        for (TextField textField: textFields){
            if (signupValidations.textValidation(textField.getText())){
                formatCheck=false;
                Alert pending = new Alert(Alert.AlertType.WARNING, "Invalid Format "+ textField.getText(), ButtonType.OK);
                pending.showAndWait();
                break;
            }
        }
        for(TextField tf : numberFields){
            if (signupValidations.IdValidation(tf.getText())){
                formatCheck=false;
                Alert pending = new Alert(Alert.AlertType.WARNING, "Invalid Format "+ tf.getText(), ButtonType.OK);
                pending.showAndWait();
                break;
            }
        }
        return formatCheck;
    }
    @FXML
    public void ShowSelected(MouseEvent event){
        ListM = donations.getSelectionModel().getSelectedItems();

        if(ListM.size() == 0){

        }else {
            for(int i = 0; i < ListM.size(); i++){
                dId.setText(String.valueOf(ListM.get(0).DonorId));
                dName.setText(ListM.get(0).donorname);
                bGroup.setValue(ListM.get(0).bloodgroup);
                rId.setText(ListM.get(0).receiverid);
                rName.setText(ListM.get(0).receivername);
                amount.setText(ListM.get(0).amount);
            }
        }
    }
    @FXML
    private void delete(ActionEvent event) throws IOException{
        boolean bln = true;
        EmptyFieldsCheck();
        tFields = new TextField[]{dId, dName, rId, rName, amount};
        for (TextField tField : tFields) {
            if (tField.getText().equals("")) {
                bln = false;
            }
        }
        Connection conn= ConnectDB();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (bln&&FormatCheck()) {
                String sql = "delete from donations where DonorId=?";
                PreparedStatement preparedstatement = conn.prepareStatement(sql);
                if (!rId.getText().equals("")) {
                    preparedstatement.setString(1, dId.getText());
                    preparedstatement.executeUpdate();
                    Alert pending = new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully", ButtonType.OK);
                    pending.showAndWait();
                    loadData();
                    refreshTable();
                    loadData();
                    for (TextField tField : tFields) {
                        {
                            tField.setText("");
                        }
                    }
                    bGroup.setValue("");
                }
                }
            } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    @FXML
    private void Update(ActionEvent event) throws IOException {
        boolean bln = true;
        EmptyFieldsCheck();
        Connection conn = MySqlConnection.ConnectDB();
            tFields = new TextField[]{dId, dName, rId, rName, amount};
            for (TextField tField : tFields) {
                if (tField.getText().equals("")) {
                    bln = false;
                }
            }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                if (bln&&FormatCheck()) {
                    String sql = "UPDATE donations SET DonorId=?,DonorName=?,BloodGroup=?,ReceiverId=?,ReceiverName=?,Amount=? WHERE donations.DonorId=?";
                PreparedStatement preparedstatement = conn.prepareStatement(sql);
                if (!rId.getText().equals("")) {
                    preparedstatement.setString(1, dId.getText());
                    preparedstatement.setString(2, dName.getText());
                    preparedstatement.setString(3, bGroup.getValue());
                    preparedstatement.setString(4, rId.getText());
                    preparedstatement.setString(5, rName.getText());
                    preparedstatement.setString(6, amount.getText());
                    preparedstatement.setString(7, dId.getText());
                    Alert pending = new Alert(Alert.AlertType.INFORMATION, "Updated Successfully", ButtonType.OK);
                    pending.showAndWait();
                        preparedstatement.executeUpdate();
                        loadData();
                        refreshTable();
                        loadData();
                    for (TextField tField : tFields) {
                        {
                            tField.setText("");
                        }
                    }  bGroup.setValue("");
                    }
                }


        }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        }
    TextField[] tFields;
    boolean Flag= false;

    void EmptyFieldsCheck() {
        tFields = new TextField[]{dId, dName, rId, rName,amount};
        for (TextField tField : tFields) {
            if (tField.getText().equals("")) {
                Flag = true;
                Alert pending = new Alert(Alert.AlertType.WARNING, "No field can be left Empty.", ButtonType.OK);
                pending.showAndWait();
                break;
            }
        }
    }
    @FXML
    public void insert(ActionEvent event) throws IOException {
        EmptyFieldsCheck();
        boolean bln = true;
        Connection conn = MySqlConnection.ConnectDB();
        try {

            tFields = new TextField[]{dId, dName, rId, rName,amount};
            for (TextField tField : tFields) {
                if (tField.getText().equals("")){
                    bln = false;
                }
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (bln&&FormatCheck()) {
            String sql = "INSERT INTO donations(donorId,donorName,bloodGroup,ReceiverId,ReceiverName,Amount)VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            preparedstatement.setString(1, dId.getText());
            preparedstatement.setString(2, dName.getText());
            preparedstatement.setString(3, bGroup.getValue());
            preparedstatement.setString(4, rId.getText());
            preparedstatement.setString(5, rName.getText());
            preparedstatement.setString(6, amount.getText());
                Alert pending = new Alert(Alert.AlertType.INFORMATION, "Inserted Successfully", ButtonType.OK);
                pending.showAndWait();
                preparedstatement.executeUpdate();
                loadData();
                refreshTable();
                loadData();
                for (TextField tField : tFields) {
                    {
                        tField.setText("");
                    }
                }
                bGroup.setValue("");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
        minusStock(bGroup.getValue(),amount.getText());
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
       refreshTable();
       loadData();
    }
}