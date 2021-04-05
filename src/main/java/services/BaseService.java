package services;

import java.sql.SQLException;

public interface BaseService<T>{
    void run(T commands) throws SQLException;
}
