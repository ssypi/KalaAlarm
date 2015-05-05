package kala.alarm.server.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class EmailAddress {
    @Id
    @GeneratedValue
    private int id;

    private String address;

    @ManyToMany(mappedBy = "subscribers", cascade = CascadeType.ALL)
    private Set<Application> applications = new HashSet<>();

    public EmailAddress() {

    }
    
    public EmailAddress(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

}
