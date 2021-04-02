package SFTP;

import com.jcraft.jsch.*;

import java.util.Vector;

public final class SFTPFileTransfer {
    private static final String remoteHost = "fe.ddns.protal.biz";
    private static final String username = "sftpuser";
    private static final String password = "hyperpass";
    private static final int port = 22;
    private static final String remoteDir = "/xml-data";
    private static final String localDir = "C:\\Users\\Asus\\Desktop\\demo\\allFiles";

    public static void filesTransfer() {
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

            Vector fileList = channelSftp.ls(remoteDir);
            for (int i = 2; i < fileList.size(); i++) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) fileList.get(i);
                channelSftp.get(entry.getFilename(), localDir);
            }
            channelSftp.exit();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        } finally {
            if (jschSession != null) {
                jschSession.disconnect();
            }
        }
        System.out.println("Files are downloaded");
    }
}