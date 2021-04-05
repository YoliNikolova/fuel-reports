package repository;

import commands.ConfigCommands;
import sql.DBconnect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfigRepository {
    public ConfigRepository(){}

    public void saveFolder(ConfigCommands commands) throws SQLException {
        String insertConfigFolder = "INSERT into config(configFolder) VALUES(?)";
        PreparedStatement stmt = DBconnect.con.prepareStatement(insertConfigFolder);
        stmt.setString(1, commands.getLocalDir());
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Local dir is saved.");
    }
}
