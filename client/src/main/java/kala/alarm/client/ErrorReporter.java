package kala.alarm.client;

/**
 * Used to manually report errors.
 * @see LogbackSendErrorAppender LogbackSendErrorAppender to send errors automatically on regular logging calls.
 */
public class ErrorReporter {
    private ApiClient apiClient;
    private int maxLength = 1000;

    public ErrorReporter() {
        apiClient = new ApiClient();
    }

    public void report(String error) {
        error = error.substring(0, Math.min(maxLength, error.length()));
        apiClient.sendError(error);
    }
}
