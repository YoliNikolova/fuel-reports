package sftp;

import com.jcraft.jsch.*;
import repository.ProcessRepository;

import java.sql.SQLException;
import java.util.Vector;

public final class SFTPFileTransfer {
    private static final String remoteHost = "fe.ddns.protal.biz";
    private static final String username = "sftpuser";
    private static final String password = "hyperpass";
    private static final int port = 22;
    private static final String remoteDir = "/xml-data";

    public static void filesTransfer(int limit) {
        Session jschSession = null;
        try {
            JSch jsch = new JSch();
            jschSession = jsch.getSession(username, remoteHost, port);
            jschSession.setConfig("StrictHostKeyChecking", "no");
            jschSession.setPassword(password);
            jschSession.connect();
            Channel sftp = jschSession.openChannel("sftp");
            sftp.connect();

            ChannelSftp channelSftp = (ChannelSftp) sftp;
            channelSftp.cd(remoteDir);

            ProcessRepository processRepository=new ProcessRepository();
            Vector fileList = channelSftp.ls(remoteDir);
            for (int i = 2; i <= limit+2; i++) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) fileList.get(i);
                channelSftp.get(entry.getFilename(),processRepository.selectConfigLocalDir());
            }
            channelSftp.exit();
        } catch (JSchException | SftpException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (jschSession != null) {
                jschSession.disconnect();
            }
        }
        System.out.println("Files are downloaded");
    }
}
