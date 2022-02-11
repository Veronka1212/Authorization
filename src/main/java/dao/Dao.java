package dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    List<T> findAll();

    Optional<T> findByParameter(K parameter) throws SQLException;

    boolean delete(K parameter);

    void update(T entity);

    T save(T entity) throws SQLException;
}