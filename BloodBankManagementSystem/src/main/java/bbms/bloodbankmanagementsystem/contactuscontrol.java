package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class contactuscontrol {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private RadioButton rad2;

    @FXML
    private RadioButton rad3;

    @FXML
    private RadioButton rad4;
    @FXML
    private RadioButton rad1;



    public void rad1(){
        if(rad1.isSelected()){
            rad2.setSelected(false);
            rad3.setSelected(false);
            rad4.setSelected(false);
        }

    }
    public void rad2(){
        if(rad2.isSelected()){
            rad1.setSelected(false);
            rad3.setSelected(false);
            rad4.setSelected(false);
        }
    }
    public void rad3(){
        if(rad3.isSelected()){
            rad1.setSelected(false);
            rad2.setSelected(false);
            rad4.setSelected(false);
        }
    }
    public void rad4(){
        if(rad4.isSelected()){
            rad1.setSelected(false);
            rad2.setSelected(false);
            rad3.setSelected(false);
        }
    }

    public void switchToStartPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
