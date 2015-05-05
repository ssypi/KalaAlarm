package kala.alarm.server.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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




    private Set<Application> applications = new HashSet<>();

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscribers", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "id")})
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

}
