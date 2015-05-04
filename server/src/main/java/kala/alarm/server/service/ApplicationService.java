package kala.alarm.server.service;

import kala.alarm.server.model.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Path("service-application")
public class ApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationService.class);
    private int lastId;

    // TODO: use database, not an in-memory list
    private List<Application> applications = new ArrayList<Application>() {{
        add(new Application(0, "Word"));
        add(new Application(1, "Excelasd"));
    }};

    public Application addApplication(Application application) {
        application.setId(generateId());
        LOG.debug("Application id {}, name: {}", application.getId(), application.getName());
        applications.add(application);
        return application;
    }

    public List<Application> getApplications() {
        return Collections.unmodifiableList(applications);
    }

    private int generateId() {
        return lastId++;
    }
}
