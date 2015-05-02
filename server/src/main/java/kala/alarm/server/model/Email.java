package kala.alarm.server.model;

public class Email {
    private final int id;
    private final String address;
    private final String software;

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
}
