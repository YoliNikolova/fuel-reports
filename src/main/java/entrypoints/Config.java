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
}
