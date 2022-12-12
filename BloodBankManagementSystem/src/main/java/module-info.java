module bbms.bloodbankmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens bbms.bloodbankmanagementsystem to javafx.fxml;
    exports bbms.bloodbankmanagementsystem;
}