package services;

import java.sql.SQLException;

public interface Process<T>{
    void run(T commands) throws SQLException;
}
