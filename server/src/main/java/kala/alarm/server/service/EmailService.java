package kala.alarm.server.service;

import kala.alarm.server.data.GenericHibernateRepository;
import kala.alarm.server.data.Repository;
import kala.alarm.server.model.EmailAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.List;

@Singleton
@Path("service-email")
public class EmailService {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private Repository<EmailAddress> emailRepository = new GenericHibernateRepository<>(EmailAddress.class);


    public EmailAddress createEmail(EmailAddress email) {
        LOG.debug("EmailAddress address {}, id: {}", email.getAddress(), email.getId());
        emailRepository.save(email);

        return email;
    }


    public List<EmailAddress> getEmails() {

        return emailRepository.getAll();

    }

    public void deleteEmail(int id) {

        emailRepository.delete(id);

    }
}
