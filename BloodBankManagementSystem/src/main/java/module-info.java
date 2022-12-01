module bbms.bloodbankmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens bbms.bloodbankmanagementsystem to javafx.fxml;
    exports bbms.bloodbankmanagementsystem;
}