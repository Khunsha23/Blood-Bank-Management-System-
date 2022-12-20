package bbms.bloodbankmanagementsystem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySqlConnection {
    public static String s1 = "SELECT * from donors";
    public static String s2 = "SELECT * from donors";

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank", "root", "");
            JOptionPane.showMessageDialog(null, "Connection Established");

            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}