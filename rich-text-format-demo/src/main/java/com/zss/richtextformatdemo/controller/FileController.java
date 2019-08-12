package com.zss.richtextformatdemo.controller;

import com.google.common.collect.Maps;
import com.zss.richtextformatdemo.common.ServerResponse;
import com.zss.richtextformatdemo.config.FtpProperties;
import com.zss.richtextformatdemo.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZSS
 * @date 2019/8/11 21:10
 * @description 文件上传 Controller
 */
@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 多文件上传
     *
     * @param request http请求
     * @return ServerResponse<List < String>>
     */
    @PostMapping("/uploads")
    public Map<String, Object> upload(@RequestParam(value = "files", required = false) List<MultipartFile> files, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        Map<String, Object> resultMap = Maps.newHashMap();
        List<String> imgList = new ArrayList<>();
        try {
            if (files != null && files.size() > 0) {
                for (MultipartFile file : files) {
                    String targetFileName = fileService.upload(file, path);
                    String imgUrl = FtpProperties.HTTP_PREFIX + targetFileName;
                    imgList.add(imgUrl);
                }
            }
            resultMap.put("errno", 0);
            resultMap.put("data", imgList);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("errno", 1);
            return resultMap;
        }
    }

    /**
     * 单张图片上传
     *
     * @param files   files
     * @param request request
     * @return ServerResponse<Map>
     */
    @PostMapping("/upload")
    public ServerResponse<Map> upload(@RequestParam(value = "files", required = false) MultipartFile files, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        Map<String, Object> fileMap = Maps.newHashMap();
        System.out.println(files.getOriginalFilename());
        String targetFileName = fileService.upload(files, path);
        String url = FtpProperties.HTTP_PREFIX + targetFileName;

        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);
        System.out.println(fileMap);

        return ServerResponse.createBySuccess(fileMap);
    }

    /**
     * 上传富文本图片
     *
     * @param file    文件流
     * @param request 亲贵
     * @return Map
     */
    @PostMapping("/rich_upload")
    public Map<String, Object> richTextImgUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, String> dataMap = Maps.newHashMap();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)) {
            resultMap.put("code", 1);
            resultMap.put("msg", "上传失败");
            return resultMap;
        }
        String url = FtpProperties.HTTP_PREFIX + targetFileName;
        dataMap.put("src", url);
        resultMap.put("code", 0);
        resultMap.put("msg", "上传成功");
        resultMap.put("data", dataMap);
        return resultMap;
    }
}


























