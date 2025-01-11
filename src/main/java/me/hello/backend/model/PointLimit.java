package me.hello.backend.model;

public class PointLimit {

    private int limit_id;
    private String member_id;
    private String month_year;
    private int total_used_points;

    // getters and setters

    public int getLimit_id() {
        return limit_id;
    }

    public void setLimit_id(int limit_id) {
        this.limit_id = limit_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMonth_year() {
        return month_year;
    }

    public void setMonth_year(String month_year) {
        this.month_year = month_year;
    }

    public int getTotal_used_points() {
        return total_used_points;
    }

    public void setTotal_used_points(int total_used_points) {
        this.total_used_points = total_used_points;
    }

    @Override
    public String toString() {
        return "PointLimit{" +
                "limit_id=" + limit_id +
                ", member_id='" + member_id + '\'' +
                ", month_year='" + month_year + '\'' +
                ", total_used_points=" + total_used_points +
                '}';
    }
}
