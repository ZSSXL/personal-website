package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.entity.Account;
import com.zss.personalspace.entity.User;
import com.zss.personalspace.service.AccountService;
import com.zss.personalspace.service.UserService;
import com.zss.personalspace.util.EncryptionUtil;
import com.zss.personalspace.vo.AccountVo;
import com.zss.personalspace.vo.UserAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2019/8/17 10:26
 * @description account controller
 */
@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    /**
     * 登录
     *
     * @param accountVo 账户实体
     * @param session   session
     * @param result    检验结果
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse login(@Valid @RequestBody AccountVo accountVo, HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else {
            Account account = accountService.getAccount(accountVo.getUsername(), EncryptionUtil.MD5EncodeUtf8(accountVo.getPassword()));
            if (account == null) {
                return ServerResponse.createByErrorMessage("登录失败");
            } else {
                User user = userService.getUser(account.getUserId());
                if (user == null) {
                    return ServerResponse.createByErrorMessage("获取用户信息失败，找客服");
                } else {
                    UserAccountVo userAccountVo = UserAccountVo.builder()
                            .userId(user.getUserId())
                            .username(user.getUsername())
                            .birthday(user.getBirthday())
                            .address(user.getAddress())
                            .email(user.getEmail())
                            .mood(user.getMood())
                            .phone(user.getPhone())
                            .headImg(user.getHeadImg())
                            .build();
                    session.setAttribute(Const.CURRENT_USER, userAccountVo);
                    // 有效时间，三天
                    session.setMaxInactiveInterval(60 * 60 * 24 * 3);
                    log.info("登录成功");
                    return ServerResponse.createBySuccess("登录成功");
                }
            }
        }
    }

    /**
     * 修改密码
     *
     * @param session  session
     * @param password 新密码
     * @return ServerResponse
     */
    @PutMapping
    public ServerResponse modifyPass(HttpSession session, @RequestParam("password") String password) {
        UserAccountVo userAccount = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccount == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else {
            Account account = accountService.getAccount(userAccount.getUsername(), EncryptionUtil.MD5EncodeUtf8(password));
            if (account != null) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.SAME_PASSWORD.getCode(), "新老密码不能一样");
            } else {
                try {
                    // 创建Account
                    accountService.createAccount(Account.builder()
                            .userId(userAccount.getUserId())
                            .username(userAccount.getUsername())
                            .password(EncryptionUtil.MD5EncodeUtf8(password))
                            .build());
                    return ServerResponse.createBySuccessMessage("修改密码成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return ServerResponse.createByError();
                }
            }
        }
    }
}
