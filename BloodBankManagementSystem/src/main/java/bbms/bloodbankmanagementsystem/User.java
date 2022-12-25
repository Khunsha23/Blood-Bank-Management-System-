package bbms.bloodbankmanagementsystem;

import javafx.event.ActionEvent;

import java.io.IOException;
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
    public  void loginValidation(ActionEvent event) throws IOException;
    public void SignupValidation(ActionEvent event);
}