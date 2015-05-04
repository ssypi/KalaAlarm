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

    private int lastId = 0;

    // TODO: use database, not an in-memory list
    private List<Email> emails = new ArrayList<Email>() {{
        add(new Email(0, "Kalamies.Kukaties@asd.fi", "Word"));
        add(new Email(1, "Samuli.Koponen@asd.fi" , "Word"));
    }};

    public Email createEmail(Email email) {
        LOG.debug("Email address {}, id: {}", email.getAddress(), email.getId());
        emailRepository.save(email);
        return email;
    }

    public void deleteEmail(int id) {
        Iterator<Email> i = emails.iterator();
        while (i.hasNext()) {
            Email email = i.next();
            if (email.getId() == id) {
                i.remove();
            }
        }
    }

    private int generateId() {
        return lastId++;
    }

    public List<Email> getEmails() {

        return emailRepository.getAll();

    }

    public void deleteEmail(int id) {

        emailRepository.delete(id);

    }
}
