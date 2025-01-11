package me.hello.backend.model;

public class PointTransaction {

    private int transaction_id;
    private String member_id;
    private String memberId;
    private String transaction_type;
    private int points;
    private String transaction_date;

    // getters and setters


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    @Override
    public String toString() {
        return "PointTransaction{" +
                "transaction_id=" + transaction_id +
                ", member_id='" + member_id + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                ", points=" + points +
                ", transaction_date='" + transaction_date + '\'' +
                '}';
    }
}
