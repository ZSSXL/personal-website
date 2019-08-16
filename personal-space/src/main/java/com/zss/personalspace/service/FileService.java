package com.zss.personalspace.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZSS
 * @date 2019/8/11 16:10
 * @description
 **/
public interface FileService {

    /**
     * 上传文件
     *
     * @param file multipartFile
     * @param path 文件路径
     * @return String
     */
    String upload(MultipartFile file, String path);
}
