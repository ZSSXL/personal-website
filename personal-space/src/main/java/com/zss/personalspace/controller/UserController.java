package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.config.FtpProperties;
import com.zss.personalspace.entity.Account;
import com.zss.personalspace.entity.User;
import com.zss.personalspace.service.AccountService;
import com.zss.personalspace.service.FileService;
import com.zss.personalspace.service.UserService;
import com.zss.personalspace.util.EncryptionUtil;
import com.zss.personalspace.util.UUIDUtil;
import com.zss.personalspace.vo.RegisterVo;
import com.zss.personalspace.vo.UserAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author ZSS
 * @date 2019/8/17 9:38
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AccountService accountService;
    private final FileService fileService;

    @Autowired
    public UserController(UserService userService, AccountService accountService, FileService fileService) {
        this.userService = userService;
        this.accountService = accountService;
        this.fileService = fileService;
    }

    /**
     * 注册用户
     *
     * @param registerVo 注册实体
     * @param file       头像文件
     * @param result     错误结果
     * @param request    request
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse register(@Valid @RequestBody RegisterVo registerVo, @RequestParam(value = "file", required = false) MultipartFile file, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else {
            // 上传头像
            String url = uploadHeadImg(file, request);

            String userId = UUIDUtil.getUUID();
            try {
                // 保存User
                userService.createUser(User.builder()
                        .userId(userId)
                        .username(registerVo.getUsername())
                        .birthday(registerVo.getBirthday())
                        .address(registerVo.getAddress())
                        .email(registerVo.getEmail())
                        .phone(registerVo.getPhone())
                        .mood(registerVo.getMood())
                        .headImg(url)
                        .build());

                // 创建Account
                accountService.createAccount(Account.builder()
                        .userId(userId)
                        .password(EncryptionUtil.MD5EncodeUtf8(registerVo.getPassword()))
                        .build());

                return ServerResponse.createBySuccessMessage("创建成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByError();
            }
        }
    }

    /**
     * 更新用户信息
     *
     * @param session       session
     * @param userAccountVo 用户账户实体
     * @param file          file
     * @param result        校验结果
     * @param request       request
     * @return ServerResponse
     */
    @PutMapping
    public ServerResponse updateUser(HttpSession session
            , @Valid @RequestBody UserAccountVo userAccountVo
            , @RequestParam(value = "file", required = false) MultipartFile file
            , BindingResult result
            , HttpServletRequest request) {
        UserAccountVo userAccount = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else if (userAccount == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            // 更新头像
            String url = uploadHeadImg(file, request);
            try {
                // 更新用户信息
                userService.createUser(User.builder()
                        .userId(userAccountVo.getUserId())
                        .username(userAccountVo.getUsername())
                        .birthday(userAccountVo.getBirthday())
                        .address(userAccountVo.getAddress())
                        .email(userAccountVo.getEmail())
                        .phone(userAccountVo.getPhone())
                        .mood(userAccountVo.getMood())
                        .headImg(url)
                        .build());
                return ServerResponse.createBySuccessMessage("创建成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByError();
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
    public ServerResponse modifyPass(HttpSession session, String password) {
        UserAccountVo userAccount = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccount == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else {
            Account account = accountService.getAccount(userAccount.getUsername(), password);
            if (account != null) {
                return ServerResponse.createByErrorMessage("新老密码不能一样");
            } else {
                try{
                    // 创建Account
                    accountService.createAccount(Account.builder()
                            .userId(userAccount.getUserId())
                            .password(EncryptionUtil.MD5EncodeUtf8(password))
                            .build());
                    return ServerResponse.createBySuccessMessage("修改密码成功");
                }catch (Exception e){
                    e.printStackTrace();
                    return ServerResponse.createByError();
                }
            }
        }
    }

    // ------------------------------ 私有工具 ------------------------------- //

    /**
     * 上传头像
     *
     * @param file    file
     * @param request request
     * @return String
     */
    private String uploadHeadImg(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        if (file != null) {
            String targetFileName = fileService.upload(file, path);
            log.info("上传文件成功:" + targetFileName);
            return FtpProperties.HTTP_PREFIX + targetFileName;
        } else {
            return null;
        }
    }
}
