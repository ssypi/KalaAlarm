package kala.alarm.server.service;

import kala.alarm.server.model.AppError;
import kala.alarm.server.model.Application;
import kala.alarm.server.model.EmailAddress;
import kala.alarm.server.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.ArrayList;
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
    private List<AppError> errors = new ArrayList<AppError>() {{
        add(new AppError("Super error", 0));
        add(new AppError("Super error2", 0));
    }};

    public void createError(AppError error) {
        LOG.debug("Error from {}, message: {}", error.getApplicationId(), error.getMessage());
        errors.add(error);
        Set<EmailAddress> recipients = applicationService.getSubscribers(error.getApplicationId());
        EmailMessage emailMessage = new EmailMessage();
        Application application = applicationService.getApplication(error.getApplicationId());
        emailMessage.setSubject("Error from: " + application.getName());
        emailMessage.setBody(error.getMessage());
        emailSender.sendEmail(emailMessage, recipients);
    }

    public List<AppError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

}
