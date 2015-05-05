package kala.alarm.server.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    private List<EmailAddress> subscribers;

    public Application() {

    }

    public Application(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "subscribers", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "id")})
    public List<EmailAddress> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<EmailAddress> subscribers) {
        this.subscribers = subscribers;
    }
}
