package kala.alarm.client;

public class ExceptionHandler {

    /**
     * Sets up the global exception handler for exceptions that are not caught elsewhere
     * Note: If you're using a web server or frameworks to handle threads, you should
     * use their specific exception handlers
     */
    public static void initGlobalExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Uncaught exception in thread " + t.getName());
            System.out.println(e);
        });
    }
}
