package services;

import java.sql.SQLException;

public interface BaseService<T>{
    void process(T commands) throws SQLException;
}
