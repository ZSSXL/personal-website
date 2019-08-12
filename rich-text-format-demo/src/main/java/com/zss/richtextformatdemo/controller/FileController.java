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
 * @description
 */
@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ServerResponse<List<String>> upload(HttpServletRequest request) {

        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println(path);
        // 上传图片
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断是否有文件上传
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles("imageFiles");
            List<String> urlList = new ArrayList<>();
            for (MultipartFile img : multipartFileList) {
                String targetFileName = fileService.upload(img, path);
                String url = FtpProperties.HTTP_PREFIX + targetFileName;
                urlList.add(url);
            }
            return ServerResponse.createBySuccess("上传成功", urlList);
        } else {
            return ServerResponse.createByErrorMessage("没用图片上传过来");
        }
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


























