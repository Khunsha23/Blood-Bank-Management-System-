package bbms.bloodbankmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class main1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(main1.class.getResource("D:\\BBMS\\Blood-Bank-Management-System-\\BloodBankManagementSystem\\src\\main\\java\\bbms\\bloodbankmanagementsystem\\startpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("OneBlood");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
