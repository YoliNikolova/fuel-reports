package commands;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import sql.DBconnect;
import sql.DBcreate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Config {
    @Parameter(names = "--data-dir")
    String localDir;

    public String getLocalDir() {
        return localDir;
    }

    public static void main(String... args) throws SQLException {
        Config config = new Config();
        JCommander.newBuilder()
                .addObject(config)
                .build()
                .parse(args);
        config.insertConfigFolder();
    }

    void insertConfigFolder() throws SQLException {
        DBcreate.createConfigTable();
        String insertConfigFolder = "INSERT into config(configFolder) VALUES(?)";
        PreparedStatement stmt = DBconnect.con.prepareStatement(insertConfigFolder);
        stmt.setString(1, getLocalDir());
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Local dir is saved.");
    }
}
