import com.jcraft.jsch.*;

import java.io.FileInputStream;

/**
 * Created by Jan.Pippal on 11/08/2016.
 */
class FTPConnection {

    private final static String USERNAME = "";
    private final static String PASSWORD = "";
    private final static String HOSTNAME = "";
    private final static int PORT = 22;
    private final static String SFTPROOT = "/sftp/cmc_exchange/";

    public ChannelSftp establishFTPConnection() {

        JSch jsch = new JSch();
        Session session;
        ChannelSftp channelSftp = null;
        try {
            session = jsch.getSession(USERNAME, HOSTNAME, PORT);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(PASSWORD);
            session.connect();

            Channel channel = session.openChannel("sftp");

            channel.connect();
            channelSftp = (ChannelSftp) channel;
        } catch (JSchException o) {
            o.printStackTrace();
        }
        return channelSftp;
    }

    public void putFileToFTPandMove(ChannelSftp channelSftp, FileInputStream fis, String path, String tempPath) {

        try {
            channelSftp.put(fis, tempPath);
            ChannelExec exec = (ChannelExec) channelSftp.getSession().openChannel("exec");
            String command = "mv " + tempPath + " " + path;

            exec.setCommand(command);
            exec.connect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }


    }
    public String getSFTPROOT() {

        return SFTPROOT;
    }


}
