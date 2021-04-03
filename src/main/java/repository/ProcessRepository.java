package repository;

import commands.ProcessCommands;
import sftp.SFTPFileTransfer;
import sql.DBcreate;
import sql.DBinsert;

import java.sql.SQLException;

public class ProcessRepository implements BaseRepository<ProcessCommands> {

    public ProcessRepository(){ }
    @Override
    public void run(ProcessCommands commands) throws SQLException {
        SFTPFileTransfer.filesTransfer(commands.getLimit());
        DBcreate crateTables = new DBcreate();
        DBinsert insertData = new DBinsert();
        System.out.println("Data is inserted");
    }
}
