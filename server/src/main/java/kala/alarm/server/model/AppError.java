package kala.alarm.server.model;

public class AppError {
    private int applicationId;
    private String message;

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
}
