package com.zss.personalspace.util;

import com.zss.personalspace.config.FtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Ftp工具类
 */
@Slf4j
public class FTPUtil {

    /**
     * 返回上传成功还是失败
     *
     * @param fileList 文件集合
     * @return boolean
     */
    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(FtpProperties.FTP_IP, FtpProperties.FTP_PORT, FtpProperties.FTP_USERNAME, FtpProperties.FTP_PASSWORD);
        return ftpUtil.uploadFile(FtpProperties.FTP_REMOTE_PATH, fileList);
    }

    /**
     * @param remotePath 使上传路径多一些
     * @param fileList   多文件
     * @return boolean
     */
    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.changeWorkingDirectory(FtpProperties.FTP_REMOTE_PATH); // 是否需要切换文件夹，如果传过来的是null，那就不切换
                ftpClient.setBufferSize(102400);      // 缓冲区
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); // 文件类型设置成二进制类型，避免出现乱码
                ftpClient.enterLocalPassiveMode();  // 打开被动模式
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis); // 存储文件
                }
            } catch (IOException e) {
                log.error("上传文件异常", e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close(); // 关闭文件流
                }
                ftpClient.disconnect(); // 关闭连接
            }
        }
        return uploaded;
    }

    // 连接ftp服务器
    private boolean connectServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            log.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

}
