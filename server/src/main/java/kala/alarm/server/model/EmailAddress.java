package kala.alarm.server.model;

import javax.persistence.*;

@Entity
@Table(name="email")
public class EmailAddress {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name="address")
    private String address;

    @Column(name="software")
    private int applicationId;

    public EmailAddress() {

    }
    
    public EmailAddress(int id, String address, int applicationId) {
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
