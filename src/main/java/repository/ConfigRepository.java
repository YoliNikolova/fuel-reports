package repository;

import commands.ConfigCommands;
import sql.DBconnect;
import sql.DBcreate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConfigRepository implements BaseRepository<ConfigCommands> {

    public ConfigRepository() { }

    @Override
    public void run(ConfigCommands commands) throws SQLException {
        DBcreate.createConfigTable();
        insertConfigFolderToDatabase(commands);
    }

    private void insertConfigFolderToDatabase(ConfigCommands commands) throws SQLException {
        String insertConfigFolder = "INSERT into config(configFolder) VALUES(?)";
        PreparedStatement stmt = DBconnect.con.prepareStatement(insertConfigFolder);
        stmt.setString(1, commands.getLocalDir());
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Local dir is saved.");
    }
}
