package kala.alarm.server.data;

import kala.alarm.server.model.EmailAddress;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by Jussi on 4.5.2015.
 */
public class EmailRepository {

    private Session hibernateSession;

    public EmailRepository() {
        hibernateSession = HibernateUtil.getSessionFactory().openSession();

    }


    public EmailAddress save(EmailAddress email) {

        hibernateSession.beginTransaction();
        hibernateSession.save(email);
        hibernateSession.getTransaction().commit();
        System.err.println("Done");

        return email;

    }

    public List<EmailAddress> getAll() {

        List list = hibernateSession.createCriteria(EmailAddress.class).list();

        return list;

    }

    public EmailAddress delete(int id) {

        EmailAddress email = (EmailAddress) hibernateSession.get(EmailAddress.class, id);
        hibernateSession.delete(email);
        hibernateSession.flush();

        return email;


    }


}
