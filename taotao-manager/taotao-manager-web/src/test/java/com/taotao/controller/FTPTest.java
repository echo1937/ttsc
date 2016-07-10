package com.taotao.controller;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Eric on 6/23/16.
 */
public class FTPTest {

    @Test
    public void testFtpClinet() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.33.10");
        ftpClient.login("ttsc", "redhat");
        FileInputStream inputStream = new FileInputStream(new File("/Users/Eric/Downloads/js.png"));
        ftpClient.changeWorkingDirectory("/usr/share/nginx/html/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.storeFile("test.png", inputStream);
        inputStream.close();

        //    "/usr/share/nginx/html/images/test.png "
        //

    }

    @Test
    public void testFtpUtil() throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("/Users/Eric/Downloads/wow.jpg"));
        FtpUtil.uploadFile("192.168.33.10", 21, "ttsc", "redhat",
                "/usr/share/nginx/html/images", "/2015/09/04", "hello.jpg", inputStream);

        // " /usr/share/nginx/html/images/2015/09/04/hello.jpg "
        // " http://192.168.33.10/images/2015/09//04/hello.jpg "

    }

}
