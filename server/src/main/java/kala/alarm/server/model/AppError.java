package kala.alarm.server.model;

import javax.persistence.*;

@Entity
public class AppError {

    @ManyToOne
    private Application application;

    private String message;

    @Id
    @GeneratedValue
    private int id;

    public AppError() {
    }

    public Application getApplication() { return application;
    }

    public void setApplication(Application applicationId) {
        this.application = applicationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
