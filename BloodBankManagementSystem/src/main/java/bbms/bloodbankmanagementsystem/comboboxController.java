package bbms.bloodbankmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class comboboxController implements Initializable {
    @FXML
    private ComboBox<String> combobox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        combobox.setItems(list); //combox1 is my combo box name
    }
    public void executar(ActionEvent actionEvent) {
        if ( combobox.getValue() == null) {
            //...
        }else {
            //...
        }
    }
}
