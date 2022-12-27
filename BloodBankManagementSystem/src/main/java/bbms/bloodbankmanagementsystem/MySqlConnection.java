package bbms.bloodbankmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Logger;

public class MySqlConnection {
    public static String s1 = "SELECT * from donors";
    public static String s2 = "SELECT * from donors";

    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank", "root", "");
            return connection;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    static ObservableList<donors> List = FXCollections.observableArrayList();

    public static ObservableList<donors> getList() {
        Connection connect= ConnectDB();
        String query = null;
        query = "SELECT * FROM donors ";
        try{
            PreparedStatement stm = connect.prepareStatement(query);
            ResultSet output = stm.executeQuery(query);

            while (output.next()){

                String ID = output.getString("IdNumber");
                String NAME = output.getString("FullName");
                String Contact = output.getString("ContactNumber");
                String email = output.getString("Email");
                String bloodGroup = output.getString("BloodGroup");
                String password = output.getString("Password");
                String city = output.getString("City");
                String gender = output.getString("Gender");
                Date birthDate = output.getDate("BirthDate");
                Date ldod = output.getDate("LastDateOfDonation");

                List.add(new donors(ID,NAME,email,password,Contact,bloodGroup,city,gender,birthDate,ldod));

            }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return List;
    }
    static String donorID;
    static ObservableList<donations> List2 = FXCollections.observableArrayList();

       public static ObservableList<donations> loadDatabase(){
        Connection connect= ConnectDB();
        String query ;
        query = "SELECT * FROM donations ";
            try{
                PreparedStatement stm = connect.prepareStatement(query);
                ResultSet output = stm.executeQuery(query);

                while (output.next()){

                    donorID = output.getString(1);
                    String donorNAME = output.getString("DonorName");
                    String BloodGroup = output.getString("BloodGroup");
                    String ReceiverID = output.getString("ReceiverId");
                    String ReceiverNAME = output.getString("ReceiverName");
                    String Amount = output.getString("Amount");
                    List2.add(new donations(donorID,donorNAME,ReceiverID,ReceiverNAME,BloodGroup,Amount));
                    }
                return List2;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }



}

