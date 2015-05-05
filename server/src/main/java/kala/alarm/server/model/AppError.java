package kala.alarm.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppError {
    private int applicationId;
    private String message;

    @Id
    @GeneratedValue
    private int id;

    public AppError() {
    }

    public AppError(String message, int applicationId) {
        this.applicationId = applicationId;
        this.message = message;
    }

    public int getApplicationId() { return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
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
