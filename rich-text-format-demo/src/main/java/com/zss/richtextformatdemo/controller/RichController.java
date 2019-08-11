package com.zss.richtextformatdemo.controller;

import com.zss.richtextformatdemo.common.ServerResponse;
import com.zss.richtextformatdemo.entity.RichText;
import com.zss.richtextformatdemo.service.RichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author ZSS
 * @date 2019/8/11 10:33
 * @description rich controller
 */
@RestController
@RequestMapping("/rich")
public class RichController {

    private final RichService richService;

    @Autowired
    public RichController(RichService richService) {
        this.richService = richService;
    }

    @PostMapping
    public ServerResponse createRichText(@RequestBody String richText) {
        if (richText == null) {
            System.out.println("没有东西过来");
            return ServerResponse.createByError();
        } else {
            String richId = UUID.randomUUID().toString();
            RichText save = RichText.builder()
                    .id(richId)
                    .rich(richText)
                    .build();
            System.out.println(save);
            try {
                RichText saved = richService.createRichText(save);
                if (saved == null) {
                    return ServerResponse.createByErrorMessage("保存失败");
                } else {
                    System.out.println(saved);
                    return ServerResponse.createBySuccessMessage("保存成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByErrorMessage("出错了");
            }
        }
    }

    @GetMapping
    public ServerResponse<RichText> getRich() {
        String richId = "1863511e-aec9-4be4-b1f4-c60cd2d457f0";
        RichText richText = richService.getRichText(richId);
        if (richText == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        } else {
            System.out.println(richText);
            return ServerResponse.createBySuccess(richText);
        }
    }
}
