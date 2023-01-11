package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    public User(int iD, String name, String email, String password, String contactNumber, String bloodGroup, String city) {

           this.ID = iD;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.BloodGroup = bloodGroup;
        this.City = city;

    }


    public User(int id, String fullName, String contactNumber, String bloodGroup, String city) {
        this.ID = id;
        this.name = fullName;
        this.contactNumber = contactNumber;
        this.BloodGroup = bloodGroup;
        this.City = city;
    }

    public User(int iD, String name, String email, String password, String contactNumber, String bloodGroup, String city, String gender, Date birthDate) {
        this.ID = iD;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.BloodGroup = bloodGroup;
        this.City = city;
        this.Gender = gender;
        this.birthDate = birthDate;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getLastDateofDonation() {
        return LastDateofDonation;
    }

    public void setLastDateofDonation(Date lastDateofDonation) {
        LastDateofDonation = lastDateofDonation;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    int ID;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;
     String name;
    String email;
    String password;
    String contactNumber;
     String BloodGroup;
    String City;
    String Gender;
     Date birthDate;
      Date LastDateofDonation;



    public User(int id,
                String name,
                String email,
                String password,
                String contactNumber,
                String bloodGroup,
                String city,
                String gender,
                Date birthDate,
                Date lastDateofDonation) {
        this.ID = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        BloodGroup = bloodGroup;
        City = city;
        Gender = gender;
        this.birthDate = birthDate;
        LastDateofDonation = lastDateofDonation;
    }


public User(){

}

}
 class donors extends User{

    public static boolean NameValidation(String TextField) {
        String Alphabets = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Character> c = new ArrayList<>();
        for (int i = 0; i < Alphabets.length(); i++) {
            c.add(Alphabets.charAt(i));
        }
        boolean flag = true;
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).equals(TextField.charAt(i))) {
                   flag = false;
            }
        }
        return flag;
    }

    public donors(int id,
                  String name,
                  String email,
                  String password,
                  String contactNumber,
                  String bloodGroup,
                  String city,
                  String gender,
                  Date birthDate) {
        super(id, name, email, password, contactNumber, bloodGroup, city, gender, birthDate);
    }
}
 class receivers extends User{


    public receivers(int iD,
                     String name,
                     String email,
                     String password,
                     String contactNumber,
                     String bloodGroup,
                     String city,
                     String gender,
                     Date birthDate) {
        super(iD, name, email, password, contactNumber, bloodGroup, city , gender , birthDate);

    }
    public receivers(int ID,String fullName, String contactNumber, String city, String bloodGroup,String s){
        super(ID, fullName,  contactNumber, bloodGroup, city);
        this.status=s;
    }


 }
interface Validation{
    public  void loginValidation(ActionEvent event) throws IOException, SQLException;
    public void SignupValidation(ActionEvent event) throws IOException;
}