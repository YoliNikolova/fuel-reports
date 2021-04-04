package entrypoints;

import commands.ProcessCommands;
import repository.ProcessRepository;
import sftp.SFTPFileTransfer;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import sql.*;

import java.sql.SQLException;

public class Process {
    public static void main(String[] args) throws SQLException {
        ProcessCommands processCommands = new ProcessCommands();
        JCommander.newBuilder()
                .addObject(processCommands)
                .build()
                .parse(args);
        ProcessRepository processRepository = new ProcessRepository();
        processRepository.run(processCommands);
    }
}
