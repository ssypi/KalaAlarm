package kala.alarm.server.data;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();

    T getById(int id);

    T save(T object);

    T update(T object);

    T delete(int id);
}
