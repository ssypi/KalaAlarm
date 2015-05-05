package kala.alarm.server.data;

import org.hibernate.Session;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class GenericHibernateRepository<T> implements Repository<T> {
    private static final Logger LOG = LoggerFactory.getLogger(GenericHibernateRepository.class);

    private Session hibernateSession;
    private Class<T> clazz;

    public GenericHibernateRepository(Class<T> clazz) {
        hibernateSession = HibernateUtil.getSessionFactory().openSession();
        this.clazz = clazz;
    }


    @Override
    public List<T> getAll() {
        List list = hibernateSession.createCriteria(clazz).list();
        return list;
    }

    @Override
    public T getById(int id) {
        return (T) hibernateSession.get(clazz, id);
    }

    @Override
    public T save(T object) {
        hibernateSession.beginTransaction();
        hibernateSession.saveOrUpdate(object);
        hibernateSession.getTransaction().commit();
        return object;
    }

    @Override
    public T update(T object) {
        // TODO: implement
        throw new NotImplementedException();
    }

    @Override
    @Nullable
    public T delete(int id) {
        T object = (T) hibernateSession.get(clazz, id);
        if (object != null) {
            hibernateSession.delete(object);
            hibernateSession.flush();
        }
        return object;
    }
}
