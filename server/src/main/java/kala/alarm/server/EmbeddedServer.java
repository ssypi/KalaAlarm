package kala.alarm.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.http.server.accesslog.AccessLogBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * Embedded HTTP server
 * Serves static files and the API
 */
public class EmbeddedServer {
    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedServer.class);

    private static final int PORT = 80;

    private static final String API_PREFIX = "/api/";
    private static final String ACCESS_LOG_FILE = "access.log";
    private static final String LOG_PATH = "log/";
    private static final String STATIC_CONTENT_FOLDER = "www";

    private static void createLogDirectory() {
        File dir = new File(LOG_PATH);
        LOG.info("Creating log directory..");
        try {
            dir.mkdir();
            LOG.info("Directory created.");
        } catch (SecurityException e) {
            LOG.error("Unable to create the log directory", e);
        }
    }


    public static void main(String[] args) throws Exception {
        final URI uri = getUri();

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("kala.alarm.server.controller", "kala.alarm.server.service");
        LOG.info("Starting HTTP Server on " + uri.toString());
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig, false);

        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler(STATIC_CONTENT_FOLDER));

        createLogDirectory();
        AccessLogBuilder builder = new AccessLogBuilder(LOG_PATH + ACCESS_LOG_FILE);
        builder.instrument(server.getServerConfiguration());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("Shutting down HTTP server..");
            server.shutdown();
            LOG.info("Done.");
        }));

        server.start();
        LOG.info("Running..");
        Thread.currentThread().join();
    }

    private static URI getUri() {
        String hostName = "localhost";
        try {
            hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        URI uri = UriBuilder.fromUri("http://" + hostName + API_PREFIX).port(PORT).build();
        return uri;
    }
}