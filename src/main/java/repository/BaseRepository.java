package repository;

import java.sql.SQLException;

public interface BaseRepository<T> {
    void run(T command) throws SQLException;
}
