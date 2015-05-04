package kala.alarm.server.data;

import kala.alarm.server.model.Email;
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


    public Email save(Email email) {

        hibernateSession.beginTransaction();
        hibernateSession.save(email);
        hibernateSession.getTransaction().commit();
        System.err.println("Done");

        return email;

    }

    public List<Email> getAll() {

        List list = hibernateSession.createCriteria(Email.class).list();

        return list;

    }

    public Email delete(int id) {

        Email email = (Email) hibernateSession.get(Email.class, id);
        hibernateSession.delete(email);
        hibernateSession.flush();

        return email;


    }


}
