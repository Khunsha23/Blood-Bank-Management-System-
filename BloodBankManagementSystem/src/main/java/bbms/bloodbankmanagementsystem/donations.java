package bbms.bloodbankmanagementsystem;

public class donations {
    String DonorId;
    String donorname;
    String receiverid;
    String receivername;
    String bloodgroup;

    public String getDonorId() {
        return DonorId;
    }

    public void setDonorId(String donorId) {
        DonorId = donorId;
    }

    public String getDonorname() {
        return donorname;
    }

    public void setDonorname(String donorname) {
        this.donorname = donorname;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount;

    public donations(String DonorId,
                     String donorname,
                     String receiverid,
                     String receivername,
                     String bloodgroup,
                     String amount) {
        this.DonorId = DonorId;
        this.donorname = donorname;
        this.receiverid = receiverid;
        this.receivername = receivername;
        this.bloodgroup = bloodgroup;
        this.amount = amount;
    }
}
