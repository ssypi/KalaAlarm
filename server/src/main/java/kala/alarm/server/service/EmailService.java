package kala.alarm.server.service;

import kala.alarm.server.data.EmailRepository;
import kala.alarm.server.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Singleton
@Path("service-email")
public class EmailService {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private EmailRepository emailRepository = new EmailRepository();


    public Email createEmail(Email email) {
        LOG.debug("Email address {}, id: {}", email.getAddress(), email.getId());
        emailRepository.save(email);
        return email;
    }


    public List<Email> getEmails() {

        return emailRepository.getAll();

    }

    public void deleteEmail(int id) {

        emailRepository.delete(id);

    }
}
