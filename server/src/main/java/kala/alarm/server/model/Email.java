package kala.alarm.server.model;

public class Email {
    private int id;
    private String address;
    private String software;

    public Email() {

    }

    public Email(int id, String address, String software) {
        this.id = id;
        this.address = address;
        this.software = software;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public String getSoftware() {
        return software;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSoftware(String software) {
        this.software = software;
    }
}
