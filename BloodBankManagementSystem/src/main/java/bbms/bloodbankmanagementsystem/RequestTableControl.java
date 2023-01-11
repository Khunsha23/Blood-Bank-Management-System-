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
import java.sql.SQLException;
import java.util.ResourceBundle;

import static bbms.bloodbankmanagementsystem.MySqlConnection.ConnectDB;

public class RequestTableControl implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<User> requests;
    @FXML
    private TableColumn<receivers, String> name;
    @FXML
    private TableColumn<receivers, String> status;

    @FXML
    private TableColumn<receivers, Integer> id;

    @FXML
    private TableColumn<receivers, String> bloodGroup;

    @FXML
    private TableColumn<receivers, String> city_t;
    @FXML
    private TableColumn<receivers, String> Contact;

    public void switchToDashBoard(ActionEvent event) throws IOException {
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
    ObservableList<User> ListM;
    @FXML
    private TextField City;
    @FXML
    private ComboBox<String> bGroup;
    @FXML
    private TextField dName;
    @FXML
    private TextField number;
    @FXML
    private TextField rId;
    @FXML
    public void ShowSelected(MouseEvent event){
        ListM = requests.getSelectionModel().getSelectedItems();

        if(ListM.size() == 0){

        }else {
            for(int i = 0; i < ListM.size(); i++){
                rId.setText(String.valueOf(ListM.get(0).ID));
                dName.setText(ListM.get(0).name);
                number.setText(ListM.get(0).contactNumber);
                City.setText(ListM.get(0).City);
                bGroup.setValue(ListM.get(0).BloodGroup);
            }
        }
    }
    TextField[] tFields;
    void EmptyFieldsCheck(){
        tFields = new TextField[]{rId, dName, number, City};
        for (TextField tField : tFields) {
            if (tField.getText().equals("")) {
                Alert pending = new Alert(Alert.AlertType.WARNING, "No field can be left Empty.", ButtonType.OK);
                pending.showAndWait();
                break;
            }
        }
    }
    TextField[] textFields;
    TextField[] numberFields;

  boolean FormatCheck(){
      boolean formatCheck =true;
        textFields = new TextField[]{ dName, City};
        numberFields = new TextField[]{ rId , number};
            for (TextField textField: textFields){
                if (signupValidations.textValidation(textField.getText())){
                    formatCheck=false;
                    Alert pending = new Alert(Alert.AlertType.WARNING, "Invalid Format in "+textField.getText(), ButtonType.OK);
                    pending.showAndWait();
                    break;
                }
                }
            for(TextField tf : numberFields){
                if (signupValidations.IdValidation(tf.getText())){
                    formatCheck=false;
                    Alert pending = new Alert(Alert.AlertType.WARNING, "Invalid Format in "+tf.getText(), ButtonType.OK);
                    pending.showAndWait();
                    break;
                }
            }
            return formatCheck;
    }

    @FXML
    private void delete(ActionEvent event) throws IOException{
        boolean bln = true;
        EmptyFieldsCheck();
        tFields = new TextField[]{rId, dName, number, City};
        for (TextField tField : tFields) {

            if (tField.getText().equals("")){
                bln = false;
            }

        }
        Connection conn= ConnectDB();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (bln&&FormatCheck()) {
            String sql= "delete from requests where ReceiverID=?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            if(!rId.getText().equals("")){

                    preparedstatement.setString(1, rId.getText());
                    preparedstatement.executeUpdate();
                    loadData();
                    refresh();
                    loadData();
                    for (TextField tField : tFields) {
                        {
                            tField.setText("");
                        }
                    }
                    Alert pending = new Alert(Alert.AlertType.INFORMATION, "Row deleted Successfully", ButtonType.OK);
                    pending.showAndWait();
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }


    @FXML
    private void Update(ActionEvent event) throws IOException {
       EmptyFieldsCheck();
        boolean bl = true;
        try {
            tFields = new TextField[]{rId, dName, number, City};
            for (TextField tField : tFields) {

                   if (tField.getText().equals("")){
                   bl = false;
                   }

            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = ConnectDB();
            String sql = "UPDATE requests SET ReceiverID=?,FullName=?,ContactNumber=?,City=?,BloodGroup=? WHERE requests.ReceiverID=?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            if(!rId.getText().equals("")){
                if (bl&&FormatCheck()) {
                preparedstatement.setString(1, rId.getText());
                preparedstatement.setString(2, dName.getText());
                preparedstatement.setString(3, number.getText());
                preparedstatement.setString(4, City.getText());
                preparedstatement.setString(5, bGroup.getValue());
                preparedstatement.setString(6, rId.getText());
                preparedstatement.executeUpdate();
                Alert pending = new Alert(Alert.AlertType.INFORMATION, "Updated Successfully", ButtonType.OK);
                pending.showAndWait();
                loadData();
                refresh();
                loadData();
                for (TextField tField : tFields) {
                    {
                        tField.setText("");
                    }
                }
                bGroup.setValue("");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public void loadData(){
        try {
            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            Contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            city_t.setCellValueFactory(new PropertyValueFactory<>("City"));
            bloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
            status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            ListM = MySqlConnection.getRequest();
            requests.setItems(ListM);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public void refresh(){
        ListM.clear();
    }
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            loadData();
        refresh();
        loadData();
    }


}
