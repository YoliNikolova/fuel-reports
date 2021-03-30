package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    public static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String username = "root";
    public static String password = "root_pass";
    Connection con = null;

    public static Connection connectDB(){
        //load driver
        try {
            Class.forName(DB_DRIVER);
        System.out.println("DB driver loaded successful.");
        //connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase1", username, password);
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

}
