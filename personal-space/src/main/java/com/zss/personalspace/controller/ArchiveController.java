package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.entity.Archive;
import com.zss.personalspace.service.ArchiveService;
import com.zss.personalspace.vo.UserAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2019/8/20 18:09
 * @description archive controller
 */
@RestController
@RequestMapping("/api/archive")
public class ArchiveController {

    private final ArchiveService archiveService;

    @Autowired
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    /**
     * 分页查询所有档案
     *
     * @param session session
     * @param page    当前页
     * @return ServerResponse<Page < Archive>>
     */
    @GetMapping
    public ServerResponse<Page<Archive>> getAllArchiveByPage(HttpSession session
            , @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            Page<Archive> archivePage = archiveService.getAllArchiveByPaging(page);
            if (archivePage == null) {
                return ServerResponse.createByErrorMessage("查询失败了");
            } else {
                return ServerResponse.createBySuccess(archivePage);
            }
        }
    }
}
