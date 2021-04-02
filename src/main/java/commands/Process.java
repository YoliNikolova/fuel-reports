package commands;

import SFTP.SFTPFileTransfer;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import sql.*;

public class Process {
    @Parameter(names = "--limit", required = true)
    int limit;

    public static void main(String[] args) {
        Process process = new Process();
        JCommander.newBuilder()
                .addObject(process)
                .build()
                .parse(args);
        process.run();
    }

    public void run() {
        SFTPFileTransfer.filesTransfer(limit);
        DBcreate crateTables = new DBcreate();
        DBinsert insertData = new DBinsert();
        System.out.println("Data is inserted");
    }
}
