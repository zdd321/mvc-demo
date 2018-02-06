package com.zdd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * FTP������
 * @author Administrator
 *
 */
public class FtpUtils {

	private static Logger logger = Logger.getLogger(FtpUtils.class);  
	  
    
    
	private FTPClient ftpClient = null;
    private String server;
    private int port;
    private String userName;
    private String userPassword;

    public FtpUtils(String server, int port, String userName, String userPassword) {
        this.server = server;
        this.port = port;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * ���ӷ�����
     * @return ���ӳɹ���� true:�ɹ��� false:ʧ��
     */
    public boolean open() {
        if (ftpClient != null && ftpClient.isConnected()) {
            return true;
        }
        try {
            ftpClient = new FTPClient();
            // ����
            ftpClient.connect(this.server, this.port);
            ftpClient.login(this.userName, this.userPassword);
            setFtpClient(ftpClient);
            // ��������Ƿ�ɹ�
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.close();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            System.out.println("open FTP success:" + this.server + ";port:" + this.port + ";name:" + this.userName
                    + ";pwd:" + this.userPassword);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // �����ϴ�ģʽ.binally  or ascii
            return true;
        } catch (Exception ex) {
            this.close();
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * �л�����Ŀ¼
     * @return �л���� true���ɹ��� false��ʧ��
     */
    private boolean changeToParentDir() {
        try {
            return ftpClient.changeToParentDirectory();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * �ı䵱ǰĿ¼��ָ��Ŀ¼
     * @param dir Ŀ��Ŀ¼
     * @return �л���� true���ɹ���false��ʧ��
     */
    private boolean cd(String dir) {
        try {
            return ftpClient.changeWorkingDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ��ȡĿ¼�����е��ļ�����
     * 
     * @param filePath ָ����Ŀ¼
     * @return �ļ��б�,����null
     */
    private FTPFile[] getFileList(String filePath) {
        try {
            return ftpClient.listFiles(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ����л�����Ŀ¼
     * @param ftpPath Ŀ��Ŀ¼
     * @return �л����
     */
    public boolean changeDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            // ��·���е�б��ͳһ
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            if (ftpPath.indexOf('/') == -1) {
                // ֻ��һ��Ŀ¼
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
            } else {
                // ���Ŀ¼ѭ������
                String[] paths = ftpPath.split("/");
                for (int i = 0; i < paths.length; i++) {
                    ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ѭ������Ŀ¼�����Ҵ�����Ŀ¼�����ù���Ŀ¼Ϊ��ǰ������Ŀ¼��
     * @param ftpPath ��Ҫ������Ŀ¼
     * @return
     */
    public boolean mkDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            // ��·���е�б��ͳһ
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            System.out.println("ftpPath:" + ftpPath);
            if (ftpPath.indexOf('/') == -1) {
                // ֻ��һ��Ŀ¼
                ftpClient.makeDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(), "iso-8859-1"));
            } else {
                // ���Ŀ¼ѭ������
                String[] paths = ftpPath.split("/");
                for (int i = 0; i < paths.length; i++) {
                    ftpClient.makeDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                    ftpClient.changeWorkingDirectory(new String(paths[i].getBytes(), "iso-8859-1"));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * �ϴ��ļ���FTP������
     * @param localDirectoryAndFileName �����ļ�Ŀ¼���ļ���
     * @param ftpFileName �ϴ������������ļ���
     * @param ftpDirectory FTPĿ¼��:/path1/pathb2/,���Ŀ¼�����ڻ��Զ�����Ŀ¼
     * @return
     */
    public boolean upload(String localDirectoryAndFileName, String ftpFileName, String ftpDirectory) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        boolean flag = false;
        if (ftpClient != null) {
            File srcFile = new File(localDirectoryAndFileName);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(srcFile);
                // ����Ŀ¼
                this.mkDir(ftpDirectory);
                ftpClient.setBufferSize(100000);
                ftpClient.setControlEncoding("UTF-8");
                // �����ļ����ͣ������ƣ�
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // �ϴ�
                flag = ftpClient.storeFile(new String(ftpFileName.getBytes(), "iso-8859-1"), fis);
            } catch (Exception e) {
                this.close();
                e.printStackTrace();
                return false;
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("�ϴ��ļ��ɹ��������ļ����� " + localDirectoryAndFileName + "���ϴ���Ŀ¼��" + ftpDirectory + "/" + ftpFileName);
        return flag;
    }

    /**
     * ��FTP�������������ļ�
     * @param ftpDirectoryAndFileName ftp�������ļ�·������/dir��ʽ��ʼ
     * @param localDirectoryAndFileName ���浽���ص�Ŀ¼
     * @return
     */
    public boolean get(String ftpDirectoryAndFileName, String localDirectoryAndFileName) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        ftpClient.enterLocalPassiveMode(); // Use passive mode as default
        try {
            // ��·���е�б��ͳһ
            char[] chars = ftpDirectoryAndFileName.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {
                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpDirectoryAndFileName = sbStr.toString();
            String filePath = ftpDirectoryAndFileName.substring(0, ftpDirectoryAndFileName.lastIndexOf("/"));
            String fileName = ftpDirectoryAndFileName.substring(ftpDirectoryAndFileName.lastIndexOf("/") + 1);
            this.changeDir(filePath);
            ftpClient.retrieveFile(new String(fileName.getBytes(), "iso-8859-1"),
                    new FileOutputStream(localDirectoryAndFileName)); // download
            // file
            System.out.println(ftpClient.getReplyString()); // check result
            System.out.println("��ftp�������������ļ���" + ftpDirectoryAndFileName + "�� ���浽��" + localDirectoryAndFileName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ����FTPĿ¼�µ��ļ��б�
     * @param pathName
     * @return
     */
    public String[] getFileNameList(String pathName) {
        try {
            return ftpClient.listNames(pathName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ɾ��FTP�ϵ��ļ�
     * @param ftpDirAndFileName ·����ͷ���ܼ�/������Ӧ����test/filename1
     * @return
     */
    public boolean deleteFile(String ftpDirAndFileName) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            return ftpClient.deleteFile(ftpDirAndFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ɾ��FTPĿ¼
     * @param ftpDirectory
     * @return
     */
    public boolean deleteDirectory(String ftpDirectory) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            return ftpClient.removeDirectory(ftpDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * �ر�����
     */
    public void close() {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            System.out.println("�ɹ��ر����ӣ�������ip:" + this.server + ", �˿�:" + this.port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public static void main(String[] args) {
        FtpUtils f = new FtpUtils("192.168.162.100", 21, "uftp", "admin");
        try {
            if(f.open()) {
                String fileName = "����2.txt";
                //�ϴ�
                f.upload("d:/1.txt", fileName, "test1");

                //����
                FTPFile[] list = f.getFileList("test1");
                for(FTPFile file : list) {
                    String name = file.getName();
                    System.out.println("--" + new String(name.getBytes("iso-8859-1"), "GB2312"));
                }

                //ֻ����ָ��Ŀ¼�µ��ļ���
                String[] names = f.getFileNameList("test1");
                for(String name : names) {
                    System.out.println(new String(name.getBytes("iso-8859-1"), "GB2312"));
                }

                //����
                boolean b = f.get("/test1/����2.txt", "d:/text.txt");
                System.out.println(b);

                //ɾ��
                String ftpDirAndFileName = "test1/����.txt";
                boolean be = f.deleteFile(new String(ftpDirAndFileName.getBytes(), "iso-8859-1"));
                System.out.println(be);

                //ɾ��Ŀ¼
                boolean delf = f.deleteDirectory("test1");
                System.out.println(delf);

                f.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
