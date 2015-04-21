package kala.alarm.server.model;

public class AppError {
    private String origin;
    private String message;

    public AppError(String message, String origin) {
        this.origin = origin;
        this.message = message;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
