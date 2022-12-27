package bbms.bloodbankmanagementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TableView<receivers> requests;
    @FXML
    private TableColumn<receivers, String> name;

    @FXML
    private TableColumn<receivers, Integer> id;

    @FXML
    private TableColumn<receivers, String> bloodGroup;

    @FXML
    private TableColumn<receivers, String> city_t;
    @FXML
    private TableColumn<receivers, String> Contact;

    public void switchToDashBoard(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<receivers> ListM;


    @FXML
    private TextField City;
    @FXML
    private TextField bGroup;
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
                bGroup.setText(ListM.get(0).BloodGroup);
            }
        }
    }
    @FXML
    private void delete(ActionEvent event) throws IOException{

        Connection conn= ConnectDB();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql= "delete from requests where ReceiverID=?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            if(!rId.getText().equals("")){
                preparedstatement.setString(1, rId.getText());
                preparedstatement.executeUpdate();
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
            String sql = "UPDATE requests SET ReceiverID=?,FullName=?,ContactNumber=?,City=?,BloodGroup=? WHERE requests.ReceiverID=?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            if(!rId.getText().equals("")){
                preparedstatement.setString(1, rId.getText());
                preparedstatement.setString(2, dName.getText());
                preparedstatement.setString(3, number.getText());
                preparedstatement.setString(4, City.getText());
                preparedstatement.setString(5, bGroup.getText());
                preparedstatement.setString(6, rId.getText());
                preparedstatement.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            id.setCellValueFactory(new PropertyValueFactory<>("ID"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            Contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
            city_t.setCellValueFactory(new PropertyValueFactory<>("City"));
            bloodGroup.setCellValueFactory(new PropertyValueFactory<>("BloodGroup"));
            ListM = MySqlConnection.getRequest();
            requests.setItems(ListM);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }


}
