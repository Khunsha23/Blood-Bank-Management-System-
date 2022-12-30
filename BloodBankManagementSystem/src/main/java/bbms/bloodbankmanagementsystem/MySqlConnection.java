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
        String query;
        query = "SELECT * FROM donors ";
        try{
            PreparedStatement stm = connect.prepareStatement(query);
            ResultSet output = stm.executeQuery(query);

            while (output.next()){

                String ID = output.getString("IdNumber");
                String NAME = output.getString("FullName");
                String Contact = output.getString("ContactNumber");
                String email = output.getString("EmailAddress");
                String bloodGroup = output.getString("BloodGroup");
                String password = output.getString("Password");
                String city = output.getString("City");
                String gender = output.getString("Gender");
                Date birthDate = output.getDate("birthday");


                List.add(new donors(Integer.parseInt(ID),NAME,email,password,Contact,bloodGroup,city,gender,birthDate));

            }
            return List;

    } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
    static String donorID;
    static ObservableList<donations> List2 = FXCollections.observableArrayList();

       public static ObservableList<donations> loadDatabase(){
        Connection connect= MySqlConnection.ConnectDB();
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
                connect.close();
                return List2;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    static ObservableList<User> List3 = FXCollections.observableArrayList();

    public static ObservableList<User> getReceiver() {
        Connection connect= ConnectDB();
        String query;
        query = "SELECT * FROM receivers ";
        try{
            PreparedStatement stm = connect.prepareStatement(query);
            ResultSet output = stm.executeQuery(query);

            while (output.next()){

                String ID = output.getString("IdNumber");
                String NAME = output.getString("FullName");
                String Contact = output.getString("ContactNumber");
                String email = output.getString("EmailAddress");
                String bloodGroup = output.getString("BloodGroup");
                String password = output.getString("Password");
                String city = output.getString("City");
                String gender = output.getString("Gender");
                Date birthDate = output.getDate("birthday");
                List3.add(new receivers(Integer.parseInt(ID),NAME,email,password,Contact,bloodGroup,city,gender,birthDate));

            }
            return List3;

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
    static ObservableList<User> List4 = FXCollections.observableArrayList();
    public static ObservableList<User> GenericList() {
        Connection connect = ConnectDB();
        String query=null;
        for(int i=0; i<2;i++){
            if(i==0)
                query = "SELECT * FROM receivers";
            if(i==1)
                query = "SELECT * FROM donors";
            try {
                PreparedStatement stm = connect.prepareStatement(query);
                ResultSet output = stm.executeQuery(query);

                while (output.next()) {

                    String ID = output.getString("IdNumber");
                    String NAME = output.getString("FullName");
                    String Contact = output.getString("ContactNumber");
                    String email = output.getString("EmailAddress");
                    String bloodGroup = output.getString("BloodGroup");
                    String password = output.getString("Password");
                    String city = output.getString("City");
                    String gender = output.getString("Gender");
                    Date birthDate = output.getDate("birthday");
                    switch (query) {
                        case "SELECT * FROM receivers" -> List4.add(new receivers(Integer.parseInt(ID), NAME, email, password, Contact, bloodGroup, city, gender, birthDate));
                        case "SELECT * FROM donors" -> List4.add(new donors(Integer.parseInt(ID), NAME, email, password, Contact, bloodGroup, city, gender, birthDate));
                    }
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
        return List4;
    }



    public static ObservableList<User> getRequest() {
        Connection connect= ConnectDB();
        String query;
        query = "SELECT * FROM requests";
        try{
            PreparedStatement stm = connect.prepareStatement(query);
            ResultSet output = stm.executeQuery(query);

            while (output.next()){
                String ID = output.getString("ReceiverID");
                String NAME = output.getString("FullName");
                String Contact = output.getString("ContactNumber");
                String city = output.getString("City");
                String bloodGroup = output.getString("BloodGroup");
                List4.add(new receivers(Integer.parseInt(ID),NAME,Contact,city,bloodGroup));
            }
            return List4;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }



}

