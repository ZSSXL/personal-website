package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.config.FtpProperties;
import com.zss.personalspace.entity.*;
import com.zss.personalspace.service.*;
import com.zss.personalspace.util.UUIDUtil;
import com.zss.personalspace.vo.AlbumDetailVo;
import com.zss.personalspace.vo.AlbumVo;
import com.zss.personalspace.vo.UserAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2019/8/18 15:32
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/api/album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumItemService albumItemService;
    private final FileService fileService;
    private final UserService userService;
    private final LikeService likeService;
    private final ArchiveService archiveService;

    @Autowired
    public AlbumController(AlbumService albumService, AlbumItemService albumItemService, FileService fileService, UserService userService, LikeService likeService, ArchiveService archiveService) {
        this.albumService = albumService;
        this.albumItemService = albumItemService;
        this.fileService = fileService;
        this.userService = userService;
        this.likeService = likeService;
        this.archiveService = archiveService;
    }

    /**
     * 新建相册
     *
     * @param session session
     * @param albumVo 相册vo 实体
     * @param files   其他图片
     * @param file    封面图片
     * @param request request
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse createAlbum(HttpSession session
            , AlbumVo albumVo
            , @RequestParam(value = "files", required = false) List<MultipartFile> files
            , BindingResult result
            , @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        UserAccountVo userAccount = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else if (result.hasErrors()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
        } else if (userAccount == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            String albumId = UUIDUtil.getUUID();
            String likeId = UUIDUtil.getUUID();
            String archiveId = UUIDUtil.getUUID();
            // 上传封面
            String url = uploadCoverImg(file, request);
            // 上传其他图片
            String urls = uploadImages(files, request);
            try {
                albumService.createAlbum(Album.builder()
                        .albumId(albumId)
                        .publish(userAccount.getUserId())
                        .coverImg(url)
                        .theme(albumVo.getTheme())
                        .type(albumVo.getType())
                        .build());
                albumItemService.createAlbumItem(AlbumItem.builder()
                        .albumId(albumId)
                        .photos(urls)
                        .build());
                likeService.createLike(Like.builder()
                        .likeId(likeId)
                        .likeOf(albumId)
                        .likeCount(Const.INIT_LIKE_COUNT)
                        .build());
                archiveService.createArchive(Archive.builder()
                        .archiveId(archiveId)
                        .theme(albumVo.getTheme())
                        .coverImg(url)
                        .archiveOf(albumId)
                        .type(Const.ArchiveType.ALBUM)
                        .build());
                log.info("上传相册成功");
                return ServerResponse.createBySuccessMessage("上传相册成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByError();
            }
        }
    }

    /**
     * 分页查询所有的相册
     *
     * @param session session
     * @param page    当前页
     * @return ServerResponse<Page < Album>>
     */
    @GetMapping
    public ServerResponse<Page<Album>> getAllByPage(HttpSession session
            , @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            Page<Album> albumPage = albumService.getAllAlbumByPaging(page);
            if (albumPage == null) {
                return ServerResponse.createByErrorMessage("查询失败了");
            } else {
                return ServerResponse.createBySuccess(albumPage);
            }
        }
    }

    /**
     * 通过id获取相册详情
     *
     * @param albumId 相册id
     * @param session session
     * @return ServerResponse<AlbumDetailVo>
     */
    @GetMapping("/{albumId}")
    public ServerResponse<AlbumDetailVo> getAlbumById(@PathVariable String albumId, HttpSession session) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            if (albumId == null) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
            } else {
                Album album = albumService.getAlbumById(albumId);
                if (album == null) {
                    return ServerResponse.createByError();
                }
                AlbumItem albumItem = albumItemService.getAlbumItemById(albumId);
                if (albumItem == null) {
                    return ServerResponse.createByError();
                }
                User user = userService.getUser(userAccountVo.getUserId());
                if (user == null) {
                    return ServerResponse.createByError();
                }
                Like like = likeService.getLikeByLikeOf(album.getAlbumId());
                if (like == null) {
                    return ServerResponse.createByError();
                }
                AlbumDetailVo albumDetailVo = AlbumDetailVo.builder()
                        .publish(user.getUsername())
                        .albumId(album.getAlbumId())
                        .coverImg(album.getCoverImg())
                        .theme(album.getTheme())
                        .createTime(album.getCreateTime())
                        .type(album.getType())
                        .photos(albumItem.getPhotos())
                        .likeId(like.getLikeId())
                        .likeCount(like.getLikeCount())
                        .build();
                return ServerResponse.createBySuccess(albumDetailVo);
            }
        }
    }


    // ------------------------------ 私有工具 ------------------------------- //

    /**
     * 上传封面
     *
     * @param file    file
     * @param request request
     * @return String
     */
    private String uploadCoverImg(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        if (file != null) {
            String targetFileName = fileService.upload(file, path);
            log.info("上传封面成功:" + targetFileName);
            return FtpProperties.HTTP_PREFIX + targetFileName;
        } else {
            return null;
        }
    }

    /**
     * 上传多张图片
     *
     * @param files   files
     * @param request request
     * @return String
     */
    private String uploadImages(List<MultipartFile> files, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        List<String> urls = new ArrayList<>();
        if (files != null && files.size() > 0) {
            for (MultipartFile file : files) {
                String targetFileName = fileService.upload(file, path);
                String imgUrl = FtpProperties.HTTP_PREFIX + targetFileName;
                urls.add(imgUrl);
            }
            return urls.toString();
        } else {
            return null;
        }
    }

}
