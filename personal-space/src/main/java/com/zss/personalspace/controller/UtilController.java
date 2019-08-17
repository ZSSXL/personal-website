package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.vo.UserAccountVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2019/8/17 12:37
 * @description
 */
@RestController
@RequestMapping("/api/util")
public class UtilController {

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
     * @param session session
     * @return ServerResponse
     */
    @PostMapping("/logout")
    public ServerResponse logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

}
