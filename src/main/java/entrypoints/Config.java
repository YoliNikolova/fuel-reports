package entrypoints;

import com.beust.jcommander.JCommander;
import commands.ConfigCommands;
import repository.ConfigRepository;
import java.sql.SQLException;

public class Config {

    public static void main(String... args) throws SQLException {
        ConfigCommands configCommands = new ConfigCommands();
        JCommander.newBuilder()
                .addObject(configCommands)
                .build()
                .parse(args);
        ConfigRepository configRepository = new ConfigRepository();
        configRepository.run(configCommands);
    }
/*
    void insertConfigFolder() throws SQLException {
        DBcreate.createConfigTable();
        String insertConfigFolder = "INSERT into config(configFolder) VALUES(?)";
        PreparedStatement stmt = DBconnect.con.prepareStatement(insertConfigFolder);
        stmt.setString(1, getLocalDir());
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Local dir is saved.");
    }

 */
}
