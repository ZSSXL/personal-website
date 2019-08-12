package com.zss.richtextformatdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZSS
 * @date 2019/8/11 8:36
 * @description
 */
@Controller
@RequestMapping("/templates")
public class HtmlController {

    @GetMapping("/{page}")
    public String pageJump(@PathVariable String page) {
        System.out.println("page:" + page);
        return page;
    }

}
