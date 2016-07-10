package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Eric on 6/28/16.
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
