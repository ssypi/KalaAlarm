package kala.alarm.server.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Application {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<EmailAddress> subscribers = new HashSet<>();

    @OneToMany
    private List<AppError> errors = new ArrayList<>();

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

    public Set<EmailAddress> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<EmailAddress> subscribers) {
        this.subscribers = subscribers;
    }

    public List<AppError> getErrors() {
        return errors;
    }

    public void setErrors(List<AppError> errors) {
        this.errors = errors;
    }
}
