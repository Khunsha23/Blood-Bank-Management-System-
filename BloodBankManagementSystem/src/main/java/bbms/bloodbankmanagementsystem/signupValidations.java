package bbms.bloodbankmanagementsystem;


import java.util.regex.Pattern;

public class signupValidations {
    public static boolean nameValidations(String name) {
        String regexPattern = "[A-Za-z\\s]+";

        boolean b = false;
        if (name == null || name.isEmpty()) {
            b = true;
        } else if (!(patternMatches(name , regexPattern))) {
        b = true;
        }
        return b;
    }
    public static boolean ContactValidation(String contact){
        String regexPattern ="^((\\+92)?(0092)?(92)?(0)?)(3)([0-9]{9})$";

        boolean b = false;
        if (contact == null || contact.isEmpty()) {
            b = true;
        } else if (!(patternMatches(contact , regexPattern))) {
            b = true;
        }
        return b;
    }
    public static boolean passwordValidation(String contact){
        String regexPattern = "^\\S{8,}$";

        boolean b = false;
        if (contact == null || contact.isEmpty()) {
            b = true;
        } else if (!(patternMatches(contact , regexPattern))) {
            b = true;
        }
        return b;
    }
     public static boolean EmailValidation(String contact){

        String regexPattern = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";

        boolean b = false;
        if (contact == null || contact.isEmpty()) {
            b = true;
        } else if (!(patternMatches(contact , regexPattern))) {
            b = true;
        }
        return b;
    }
    public static boolean patternMatches(String Name, String pattern) {
        return Pattern.compile(pattern)
                .matcher(Name)
                .matches();
    }
}

