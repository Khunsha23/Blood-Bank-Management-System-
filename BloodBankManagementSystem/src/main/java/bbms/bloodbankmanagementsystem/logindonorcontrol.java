package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class logindonorcontrol {

        private Parent text;
        private Stage stage;
        private Scene scene;
        private Parent root;

        public void switchToStartPage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    public void switchToDonorInfo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Donorinfo.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    }

