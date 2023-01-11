package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class forgotpass extends logindonorcontrol {
    @FXML
    private Label PhonenumberMsg;

    @FXML
    private TextField contact;

    @FXML
    private TextField fp;

    @FXML
    private Label passwordMsg;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchtohome(ActionEvent event) throws IOException {


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
    @FXML
    private Label Passserr;
    @FXML
    private TextField contact1;
    @FXML
    private TextField fp1;
    @FXML
    private Label numErr;
    public void update(ActionEvent e) {
        numErr.setText("");
        Passserr.setText("");
        Connection conn = MySqlConnection.ConnectDB();
        try {

            if (contact1.getText().equals("")) {
                numErr.setText("Empty Field");
            } else if (ContactValidation(contact1.getText())) {
                numErr.setText("Invalid format");
            }
            if (fp1.getText().isEmpty()) {
                Passserr.setText("Empty Field");
            } else if (passwordValidation(fp1.getText())) {
                Passserr.setText("At least 8 characters without space");
            }
            if (!ContactValidation(contact1.getText())) {
                if (!passwordValidation(fp1.getText())) {
                    String sql = "UPDATE donors SET Password=? WHERE ContactNumber=" + mobileNumber;
                    PreparedStatement preparedstatement = conn.prepareStatement(sql);
                    if (!fp1.getText().equals("")) {
                        preparedstatement.setString(1, fp1.getText());
                        preparedstatement.executeUpdate();
                        root = FXMLLoader.load(getClass().getResource("logindonor.fxml"));
                        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            }
        } catch (SQLException exception) {
            numErr.setText("This Number does not exist");
            throw new RuntimeException(exception);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update2(ActionEvent e) {
        Connection conn = MySqlConnection.ConnectDB();
        PhonenumberMsg.setText("");
        passwordMsg.setText("");

        if (contact.getText().equals("")) {
            PhonenumberMsg.setText("Empty Field");
        } else if (ContactValidation(contact.getText())) {
            PhonenumberMsg.setText("Invalid format");
        }
        if (fp.getText().isEmpty()) {
            passwordMsg.setText("Empty Field");
        } else if (passwordValidation(fp.getText())) {
            passwordMsg.setText("minimum 8 characters without space");
        }
        try {
            if (!ContactValidation(contact.getText())) {
                if (!passwordValidation(fp.getText())) {
                    String sql = "UPDATE receivers SET Password=? WHERE ContactNumber=" + loginreceivercontrol.contact;
                    PreparedStatement preparedstatement = conn.prepareStatement(sql);
                    preparedstatement.setString(1, fp.getText());
                    preparedstatement.executeUpdate();
                    root = FXMLLoader.load(getClass().getResource("loginreceiver.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        } catch (SQLException | IOException exception) {
            PhonenumberMsg.setText("This Number does not exist");
                        throw new RuntimeException(exception);
        }
    }
}