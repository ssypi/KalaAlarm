package kala.alarm.server.service;

import kala.alarm.server.data.GenericHibernateRepository;
import kala.alarm.server.data.Repository;
import kala.alarm.server.model.Application;
import kala.alarm.server.model.EmailAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.*;

@Singleton
@Path("service-application")
public class ApplicationService {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationService.class);
    private Repository<Application> applicationRepository = new GenericHibernateRepository<>(Application.class);

    public Application addApplication(Application application) {
        LOG.debug("Application id {}, name: {}", application.getId(), application.getName());
        applicationRepository.save(application);
        return application;
    }

    public void addSubscriber(int applicationId, EmailAddress email) {
        Application application = applicationRepository.getById(applicationId);
        Set<EmailAddress> subscribers = application.getSubscribers();
        subscribers.add(email);
        applicationRepository.save(application);
    }

    public Set<EmailAddress> getSubscribers(int applicationId) {
        Application application = applicationRepository.getById(applicationId);
        return application.getSubscribers();
    }

    public Application getApplication(int applicationId) {
        return applicationRepository.getById(applicationId);
    }

    public List<Application> getApplications() {

        return applicationRepository.getAll();

    }

    public EmailAddress deleteSubscriber(int applicationId, int emailId) {
        Application application = applicationRepository.getById(applicationId);
        Set<EmailAddress> subscribers = application.getSubscribers();

        Iterator<EmailAddress> i = subscribers.iterator();
        while (i.hasNext()) {
            EmailAddress emailAddress = i.next();
            if (emailAddress.getId() == emailId) {
                i.remove();
                applicationRepository.save(application);
                return emailAddress;
            }
        }
        return null;
    }
}
