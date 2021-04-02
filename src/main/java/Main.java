import SFTP.SFTPFileTransfer;
import sql.DBcreate;
import sql.DBinsert;

public class Main {
    public static void main(String[] args) {
        SFTPFileTransfer.filesTransfer();
        DBcreate crateTables = new DBcreate();
        DBinsert insertData = new DBinsert();
        System.out.println("Done: Insert data");
    }

}
