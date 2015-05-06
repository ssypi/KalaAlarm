package kala.alarm.server.service;

import kala.alarm.server.data.GenericHibernateRepository;
import kala.alarm.server.data.Repository;
import kala.alarm.server.model.AppError;
import kala.alarm.server.model.Application;
import kala.alarm.server.model.EmailAddress;
import kala.alarm.server.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Singleton
@Path("service-error")
public class ErrorService {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorService.class);
    private EmailSender emailSender = new EmailSender();
    private ApplicationService applicationService = new ApplicationService();


    // TODO: use database, not an in-memory list
    private Repository<AppError> errorRepository = new GenericHibernateRepository<>(AppError.class);



    public void createError(AppError error) {
        LOG.debug("Error from {}, message: {}", error.getApplication(), error.getMessage());
        errorRepository.save(error);
        Set<EmailAddress> recipients = applicationService.getSubscribers(error.getApplication().getId());
        EmailMessage emailMessage = new EmailMessage();
        Application application = applicationService.getApplication(error.getApplication().getId());
        emailMessage.setSubject("Error from: " + application.getName());
        emailMessage.setBody(error.getMessage());
        emailSender.sendEmail(emailMessage, recipients);
    }

    public List<AppError> getErrors() {
        return Collections.unmodifiableList(errorRepository.getAll());
    }

}
