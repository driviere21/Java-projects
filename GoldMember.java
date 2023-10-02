package Model;

public class GoldMember extends Customer{
    private String memberStatus;

    public GoldMember(int customerId, String customerName, String address, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdateBy, int divisionId, String memberStatus) {
        super(customerId, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy, divisionId);
        this.memberStatus = memberStatus;
    }


    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
}
