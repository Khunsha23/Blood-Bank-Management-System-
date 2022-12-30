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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signupcontroller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ComboBox<String> citybox;
    @FXML
    private ComboBox<?> BGComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Lahore","Karachi","Islamabad");
        citybox.setItems(list);
    }
    public void switchToDonorinfo(ActionEvent event) throws IOException {
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

}