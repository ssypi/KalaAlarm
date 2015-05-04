package kala.alarm.server.model;

public class Email {
    private int id;
    private String address;
    private int applicationId;

    public Email() {

    }

    public Email(int id, String address, int applicationId) {
        this.id = id;
        this.address = address;
        this.applicationId = applicationId;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
