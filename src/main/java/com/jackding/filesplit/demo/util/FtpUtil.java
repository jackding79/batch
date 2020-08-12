package com.jackding.filesplit.demo.util;

import com.jackding.filesplit.demo.properties.FtpProperties;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.*;
import java.net.MalformedURLException;

public class FtpUtil implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private FtpProperties properties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        properties=applicationContext.getBean(FtpProperties.class);
    }
    public FTPClient ftpClient = null;

    public void initFtpClient(){
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            System.out.println("connecting...ftp服务器:"+properties.getUrl()+":"+21);
            ftpClient.connect(properties.getUrl(), 21); //连接ftp服务器
            ftpClient.login(properties.getUsername(), properties.getPassword()); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp服务器");
            }
            System.out.println("connect successful...ftp服务器");
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean upLoadFile(String fileName,String originFileName){
        String pathName=properties.getPath();
        boolean flag = false;
        InputStream inputStream = null;
        try{
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originFileName));
            initFtpClient();
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            ftpClient.logout();
            flag = true;
            System.out.println("上传文件成功");
        }catch (Exception e) {
            System.out.println("上传文件失败");
            e.printStackTrace();
        }finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /** * 下载文件 *
     * @param pathname FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return */
    public  boolean downloadFile(String filename, String localPath){
        boolean flag = false;
        OutputStream os=null;
        String pathname=properties.getPath();
        try {
            System.out.println("开始下载文件");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localPath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftpClient.logout();
            flag = true;
            System.out.println("下载文件成功");
        } catch (Exception e) {
            System.out.println("下载文件失败");
            e.printStackTrace();
        } finally{
            if(ftpClient.isConnected()){
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
