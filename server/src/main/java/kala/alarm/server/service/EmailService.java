package kala.alarm.server.service;

import kala.alarm.server.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Path("service-email")
public class EmailService {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    // TODO: use database, not an in-memory list
    private List<Email> emails = new ArrayList<Email>() {{
        add(new Email(0, "Kalamies.Kukaties@asd.fi", "Word"));
        add(new Email(1, "Samuli.Koponen@asd.fi" , "Word"));
    }};

    public void createEmail(Email email) {
        LOG.debug("Email address {}, id: {}", email.getAddress(), email.getId());
        emails.add(email);
    }

    public List<Email> getEmails() {
        return Collections.unmodifiableList(emails);
    }
}
