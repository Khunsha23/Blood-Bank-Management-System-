module bbms.bloodbankmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.jfoenix;
    requires jasperreports;
requires java.xml;
    requires java.management;
    requires mysql.connector.j;


    opens bbms.bloodbankmanagementsystem to javafx.fxml;
    exports bbms.bloodbankmanagementsystem;
}