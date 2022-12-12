package bbms.bloodbankmanagementsystem;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    public class controller implements Initializable {

        private Stage stage;
        private Scene scene;
        private Parent root;

        public void switchToPage1(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("firstpage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public void switchToScene2(ActionEvent event) throws IOException {

                Parent secondView;
                secondView= (AnchorPane) FXMLLoader.load(getClass().getResource("secondpage.fxml"));
                Scene newScene= new Scene(secondView);
                Stage stage2=(Stage) rootPane.getScene().getWindow();
                stage2.setScene(newScene);

        }
        @FXML
        private AnchorPane rootPane;
        private void button(ActionEvent event){
            fadeout();
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
        @FXML
        public void fadeout(){
            FadeTransition fade= new FadeTransition(Duration.millis(300));
            fade.setNode(rootPane);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        switchToScene2(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            fade.play();
        }
    }


