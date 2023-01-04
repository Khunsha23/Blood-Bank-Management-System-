package bbms.bloodbankmanagementsystem;

public class BloodBanks {
    String City;
    String Area;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public BloodBanks(String city, String area) {
        City = city;
        Area = area;
    }

}
