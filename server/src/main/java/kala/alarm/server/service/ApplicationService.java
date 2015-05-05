package kala.alarm.server.service;

import kala.alarm.server.model.Application;
import kala.alarm.server.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.*;

@Singleton
@Path("service-application")
public class ApplicationService {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationService.class);
    private int lastId;
    private Map<Integer, List<Email>> subscribers = new HashMap<>();

    // TODO: use database, not an in-memory list
    private List<Application> applications = new ArrayList<Application>() {{
        add(new Application(0, "Word"));
        add(new Application(1, "Excelasd"));
    }};

    {
        addSubscriber(0, new Email(0, "Word1", 0));
        addSubscriber(0, new Email(1, "Word2", 0));
        addSubscriber(1, new Email(2, "Excel1", 1));
        addSubscriber(1, new Email(3, "Excel2", 1));
        addSubscriber(1, new Email(4, "Excel3", 1));
    }

    public Application addApplication(Application application) {
        application.setId(generateId());
        LOG.debug("Application id {}, name: {}", application.getId(), application.getName());
        applications.add(application);
        return application;
    }

    public void addSubscriber(int applicationId, Email email) {
        List<Email> emails = subscribers.get(applicationId);
        if (emails == null) {
            emails = new ArrayList<>();
            subscribers.put(applicationId, emails);
        }
        // TODO: Check if email already exists
        emails.add(email);
    }

    public List<Email> getSubscribers(int applicationId) {
        return subscribers.get(applicationId);
    }

    public List<Application> getApplications() {
        return Collections.unmodifiableList(applications);
    }

    private int generateId() {
        return lastId++;
    }
}
