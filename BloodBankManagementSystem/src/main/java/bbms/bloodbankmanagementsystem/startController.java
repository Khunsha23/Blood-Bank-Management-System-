package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class startController
{


    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane root1;
    private Parent root;
    AnchorPane reportAnchorPane;

    public void switchToAdminLogin(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("AdminLogin.fxml"));
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

    public void switchToContactUs(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("contactus.fxml"));
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
    /*public void switchToSignUpDonor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUpPage.fxml"));
        stage = (Stage) root1.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     */
    public void switchToSignInDonor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("logindonor.fxml"));
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
    /*public void switchToSignUpReceiver(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpReceiver.fxml"));
        stage = (Stage) root1.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     */
    public void switchToSignInReceiver(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader1 = new FXMLLoader(main1.class.getResource("loginreceiver.fxml"));
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
    @FXML
    private Button closeButton;
    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    /*public void switchToInventoryReciever(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("receiverInventory.fxml"));
        stage = (Stage) root1.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToInventoryDonor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("donorInventory.fxml"));
        stage = (Stage) root1.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


     */
}


