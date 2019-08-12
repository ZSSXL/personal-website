package com.zss.richtextformatdemo.service.impl;

import com.google.common.collect.Lists;
import com.zss.richtextformatdemo.service.FileService;
import com.zss.richtextformatdemo.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author ZSS
 * @date 2019/8/11 16:10
 * @description file service impl
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {


    @Override
    public String upload(MultipartFile file, String path) {
        // 获取原始文件的文件名
        String fileName = file.getOriginalFilename();
        // 获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        // System.out.println(fileExtensionName);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        // System.out.println(uploadFileName);
        log.info("开始上传文件,上传文件的源文件名是:{},上传的路径是:{},上传后的文件名:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            // 至此，文件已经上传至upload文件夹成功
            // 将targetFie上传到vsftpd服务器上
            // System.out.println("走到了这了");
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            // 上一行代码运行解说说明已经将图片上传至ftp服务器上
            // 上传完成后删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常",e);
        }
        return targetFile.getName();
    }
}
