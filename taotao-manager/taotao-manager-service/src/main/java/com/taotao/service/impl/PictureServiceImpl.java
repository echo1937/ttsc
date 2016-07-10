package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 6/28/16.
 */

@Service
public class PictureServiceImpl implements PictureService {

//    @Value("${FTP_ADDRESS}")
//    private String FTP_ADDRESS;
//
//    @Value("${FTP_PORT}")
//    private Integer FTP_PORT;
//
//    @Value("${FTP_USERNAME}")
//    private String FTP_USERNAME;
//
//    @Value("${FTP_PASSWORD}")
//    private String FTP_PASSWORD;
//
//    @Value("${FTP_BASE_PATH}")
//    private String FTP_BASE_PATH;
//
//    @Value("${IMAGE_BASE_URL}")
//    private String IMAGE_BASE_URL;


    private String FTP_ADDRESS = "192.168.33.10";
    private Integer FTP_PORT = 21;
    private String FTP_USERNAME = "ttsc";
    private String FTP_PASSWORD = "redhat";
    private String FTP_BASE_PATH = "/usr/share/nginx/html/images";
    private String IMAGE_BASE_URL = "http://192.168.33.10/images";


    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap<>();

        try {
            // 生成一个新的文件名

            // 取原始文件名
            String oldName = uploadFile.getOriginalFilename();

            //生成新文件名
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf('.'));

            // 图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
                    imagePath, newName, uploadFile.getInputStream());

            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }

            resultMap.put("error", 0);
            resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
            return resultMap;

        } catch (Exception e) {
            // TODO
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }

}