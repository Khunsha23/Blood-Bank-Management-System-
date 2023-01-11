package bbms.bloodbankmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class MySqlConnection {

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

    public void minusStock(String BloodGroup,String Amount){
        Connection conn = MySqlConnection.ConnectDB();

        try {
            int amount_donations = Integer.parseInt(Amount);
            int amount_inventory = 0;
            String sql="SELECT Amount FROM inventory WHERE BloodGroup = '" +BloodGroup+"'";
            PreparedStatement stm1 = conn.prepareStatement(sql);
            ResultSet output2= stm1.executeQuery();
            while (output2.next()){
                amount_inventory= Integer.parseInt(output2.getString("Amount"));
            }
            String sql2="UPDATE inventory SET Amount=? WHERE BloodGroup= '"+BloodGroup+"'";
            PreparedStatement stm2= conn.prepareStatement(sql2);
            String UpdateAmount= String.valueOf(amount_inventory - amount_donations);
                stm2.setString(1,UpdateAmount);
                stm2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }}
    public static void InsertReceiver( String FullName, String ContactNumber ,
                                       String  EmailAddress , String BloodGroup,
                                       String Password ,String City , String Gender,
                                       Date DoB) throws SQLException {
        Connection conn = ConnectDB();
        String sql = "INSERT INTO receivers(FullName,ContactNumber,EmailAddress,BloodGroup,City,Gender,birthday,Password)VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedstatement = conn.prepareStatement(sql);
        preparedstatement.setString(1, FullName);
        preparedstatement.setString(2, ContactNumber);
        preparedstatement.setString(3, EmailAddress);
        preparedstatement.setString(4, BloodGroup);
        preparedstatement.setString(5, City);
        preparedstatement.setString(6, Gender);
        preparedstatement.setString(7, String.valueOf(DoB));
        preparedstatement.setString(8, Password);
        preparedstatement.executeUpdate();

    }

    public static ObservableList<BloodBanks> BloodBanks(){
        ObservableList<BloodBanks> Lt = FXCollections.observableArrayList();
        try{
            Connection conn = ConnectDB();
            String sql = "SELECT * FROM bloodcamps";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            ResultSet output = preparedstatement.executeQuery();
            while (output.next()){
                Lt.add(new BloodBanks(output.getString(1),output.getString(2)));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    return Lt;
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
                numberOfDonations=0;
                while (output.next()){
                    donorID = output.getString(1);
                    String donorNAME = output.getString("DonorName");
                    String BloodGroup = output.getString("BloodGroup");
                    String ReceiverID = output.getString("ReceiverId");
                    String ReceiverNAME = output.getString("ReceiverName");
                    String Amount = output.getString("Amount");
                    List2.add(new donations(donorID,donorNAME,ReceiverID,ReceiverNAME,BloodGroup,Amount));
                    numberOfDonations++;
                    }
                connect.close();
                return List2;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    static void getNumbersFromDatabase(){
           getRequest();
           getStock();
           loadDatabase();

    }
    static int numberOfRequests;
    static int numberOfDonations;
    static  int numberOfLitresInInventory;
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

            numberOfRequests = 0;
            while (output.next()){
                String ID = output.getString("ReceiverID");
                String NAME = output.getString("FullName");
                String Contact = output.getString("ContactNumber");
                String city = output.getString("City");
                String bloodGroup = output.getString("BloodGroup");
                List4.add(new receivers(Integer.parseInt(ID),NAME,Contact,city,bloodGroup));
                numberOfRequests++;
            }
            return List4;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Stock>getStock(){


        ArrayList<Stock> stockList = new ArrayList<>();
        Connection connection = ConnectDB();
        String query = "SELECT * FROM inventory";
        try{
            numberOfLitresInInventory=0;
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet output = stm.executeQuery(query);

            while (output.next()){
                String BG = output.getString("BloodGroup");
                String Amount = output.getString("Amount");
                stockList.add(new Stock(Integer.parseInt(Amount),BG));
               // numberOfLitresInInventory = Integer.parseInt(numberOfLitresInInventory+Amount);
                numberOfLitresInInventory = numberOfLitresInInventory + Integer.parseInt(Amount);
            }

            return stockList;
                } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}