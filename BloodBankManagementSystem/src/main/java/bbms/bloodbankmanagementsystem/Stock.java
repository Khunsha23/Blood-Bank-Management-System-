package bbms.bloodbankmanagementsystem;

public class Stock {
    int amount;
    String BloodGroup;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public Stock(int amount, String bloodGroup) {
        this.amount = amount;
        BloodGroup = bloodGroup;
    }
}
