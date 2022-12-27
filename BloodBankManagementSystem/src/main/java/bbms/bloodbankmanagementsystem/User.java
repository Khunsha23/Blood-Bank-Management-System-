package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String contactNumber;
    private String BloodGroup;
    private String City;
    private String Gender;
    private Date birthDate;
    private  Date LastDateofDonation;

    private int amount;

    public User(int amount){
        this.amount=amount;
    }
    public User(String bloodGroup){
        this.BloodGroup= bloodGroup;
    }
    public User(String id, String name){
        this.id= id;
        this.name= name;

    }

    public User(String id,
                String name,
                String email,
                String password,
                String contactNumber,
                String bloodGroup,
                String city,
                String gender,
                Date birthDate,
                Date lastDateofDonation) {
        this.id = id;
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

    public donors(String id, String name, String email, String password, String contactNumber, String bloodGroup, String city, String gender, Date birthDate, Date lastDateofDonation) {
        super(id, name, email, password, contactNumber, bloodGroup, city, gender, birthDate, lastDateofDonation);
    }
}
 class receivers extends User{

    public receivers(String id, String name, String email, String password, String contactNumber, String bloodGroup, String city, String gender, Date birthDate, Date lastDateofDonation) {
        super(id, name, email, password, contactNumber, bloodGroup, city, gender, birthDate, lastDateofDonation);
    }
}
interface Validation{
    public  void loginValidation(ActionEvent event) throws IOException, SQLException;
    public void SignupValidation(ActionEvent event);
}