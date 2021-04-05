package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBconnect {
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root_pass";
    private final static String URL = "jdbc:mysql://localhost:3306/mydatabase1";

    public static Connection con = connectDB();
    private static Connection connectDB() {
        try {
            //load driver
            Class.forName(DB_DRIVER);
            //connection
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

}
