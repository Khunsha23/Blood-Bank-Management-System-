 package bbms.bloodbankmanagementsystem;
 import javafx.application.Application;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Scene;
 import javafx.stage.Stage;

 import java.io.IOException;

public class main1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(main1.class.getResource("startPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("OneBlood");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        MySqlConnection.ConnectDB();
        launch();

    }
}
