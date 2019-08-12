package com.zss.richtextformatdemo.controller;

import com.google.common.collect.Maps;
import com.zss.richtextformatdemo.common.ServerResponse;
import com.zss.richtextformatdemo.config.FtpProperties;
import com.zss.richtextformatdemo.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
            resultMap.put("errno",1);
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
        //System.out.println("size:"+files.length);
        System.out.println("files:" + files);
        System.out.println(path);
        Map<String, Object> fileMap = Maps.newHashMap();
        System.out.println("file:" + files);
        System.out.println(files.getOriginalFilename());
        System.out.println(files.toString());
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
    public Map richTextImgUpload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultMap = Maps.newHashMap();
        // 富文本中对于返回值有自己的要求，使用wangEditor所以按照该要求进行返回
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file, path);
        if (StringUtils.isBlank(targetFileName)) {
            resultMap.put("success", false);
            resultMap.put("msg", "上传失败");
            return resultMap;

        }
        String url = FtpProperties.HTTP_PREFIX + targetFileName;
        resultMap.put("success", true);
        resultMap.put("msg", "上传成功");
        resultMap.put("file_path", url);
        response.addHeader("Access-Controller-Allow-Header", "X-File-Name"); // 与前端的约定，必须要有
        return resultMap;
    }

}


























