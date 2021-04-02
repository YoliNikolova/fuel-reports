package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSelect {
     private static String configLocalDir;

    public static String selectConfigLocalDir() throws SQLException {
        String sql = "SELECT configFolder FROM config LIMIT 1";
        PreparedStatement stmt = DBconnect.con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            configLocalDir = rs.getString("configFolder");
        }
        return configLocalDir;
    }
}
