package services;

import commands.ConfigCommands;
import repository.ConfigRepository;
import sql.DBcreate;

import java.sql.SQLException;

public class ConfigService implements BaseService<ConfigCommands> {
    private ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public void process(ConfigCommands commands) throws SQLException {
        DBcreate.createConfigTable();
        configRepository.saveFolder(commands);
    }
}
