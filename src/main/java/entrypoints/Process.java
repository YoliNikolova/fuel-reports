package entrypoints;

import commands.ProcessCommands;
import repository.ProcessRepository;
import services.ProcessService;
import com.beust.jcommander.JCommander;


import java.sql.SQLException;

public class Process {
    public static void main(String[] args) throws SQLException {
        ProcessCommands processCommands = new ProcessCommands();
        JCommander.newBuilder()
                .addObject(processCommands)
                .build()
                .parse(args);
        ProcessService processService = new ProcessService(new ProcessRepository());
        processService.run(processCommands);
    }
}
