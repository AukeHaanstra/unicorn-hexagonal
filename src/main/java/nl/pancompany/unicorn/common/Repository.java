package nl.pancompany.unicorn.common;

public interface Repository<T, ID> {
    T find(ID id);

    T add(T t);

    T update(T t);

    long count();
}
