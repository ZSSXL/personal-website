package com.zss.personalspace.controller;

import com.google.common.collect.Maps;
import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.config.FtpProperties;
import com.zss.personalspace.service.FileService;
import com.zss.personalspace.vo.UserAccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ZSS
 * @date 2019/8/17 12:37
 * @description 工具 controller
 */
@RestController
@RequestMapping("/api/util")
public class UtilController {

    private final FileService fileService;

    @Autowired
    public UtilController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 检验是否登录
     *
     * @param session session
     * @return ServerResponse
     */
    @PostMapping("/isLogin")
    public ServerResponse isLogin(HttpSession session) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            return ServerResponse.createBySuccess();
        }
    }

    /**
     * 退出登录
     *
     * @param session session
     * @return ServerResponse
     */
    @PostMapping("/logout")
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 多文件上传 wangEditor
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
     * 上传富文本图片 layui
     *
     * @param file    文件流
     * @param request 亲贵
     * @return Map
     */
    @PostMapping("/upload")
    public Map<String, Object> richTextImgUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
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
