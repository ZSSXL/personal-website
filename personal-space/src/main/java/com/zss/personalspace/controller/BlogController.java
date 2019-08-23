package com.zss.personalspace.controller;

import com.zss.personalspace.common.Const;
import com.zss.personalspace.common.ResponseCode;
import com.zss.personalspace.common.ServerResponse;
import com.zss.personalspace.config.FtpProperties;
import com.zss.personalspace.entity.*;
import com.zss.personalspace.service.*;
import com.zss.personalspace.util.UUIDUtil;
import com.zss.personalspace.vo.BlogDetailVo;
import com.zss.personalspace.vo.BlogVo;
import com.zss.personalspace.vo.UserAccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2019/8/18 9:48
 * @description blog controller
 */
@Slf4j
@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final FileService fileService;
    private final BlogService blogService;
    private final BlogItemService blogItemService;
    private final UserService userService;
    private final LikeService likeService;
    private final ArchiveService archiveService;

    @Autowired
    public BlogController(FileService fileService, BlogService blogService, BlogItemService blogItemService, UserService userService, LikeService likeService, ArchiveService archiveService) {
        this.fileService = fileService;
        this.blogService = blogService;
        this.blogItemService = blogItemService;
        this.userService = userService;
        this.likeService = likeService;
        this.archiveService = archiveService;
    }

    /**
     * 上传日志
     *
     * @param blogVo  blogVo
     * @param file    file
     * @param request request
     * @param session session
     * @return ServerResponse
     */
    @PostMapping
    public ServerResponse createBlog(BlogVo blogVo
            , @RequestParam(value = "file", required = false) MultipartFile file
            , HttpServletRequest request
            , HttpSession session) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {

            String blogId = UUIDUtil.getUuid();
            String likeId = UUIDUtil.getUuid();
            String archiveId = UUIDUtil.getUuid();

            String url = uploadCoverImg(file, request);

            try {
                blogService.createBlog(Blog.builder()
                        .blogId(blogId)
                        .author(userAccountVo.getUserId())
                        .coverImg(url)
                        .theme(blogVo.getTheme())
                        .build());
                blogItemService.createBlogItem(BlogItem.builder()
                        .blogId(blogId)
                        .content(blogVo.getContent())
                        .build());
                likeService.createLike(Like.builder()
                        .likeId(likeId)
                        .likeOf(blogId)
                        .likeCount(Const.INIT_LIKE_COUNT)
                        .build());
                archiveService.createArchive(Archive.builder()
                        .archiveId(archiveId)
                        .archiveOf(blogId)
                        .coverImg(url)
                        .theme(blogVo.getTheme())
                        .type(Const.ArchiveType.BLOG)
                        .build());
                log.info("上传日志成功");
                return ServerResponse.createBySuccessMessage("上传成功");
            } catch (Exception e) {
                e.printStackTrace();
                return ServerResponse.createByError();
            }
        }
    }

    /**
     * 分页查询博客
     *
     * @param session session
     * @param page    page
     * @return ServerResponse<Page < Blog>>
     */
    @GetMapping
    public ServerResponse<Page<Blog>> getAllBlogByPage(HttpSession session
            , @RequestParam(value = "page", defaultValue = Const.DEFAULT_PAGE_NUMBER) Integer page) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            Page<Blog> blogPage = blogService.getAllByPaging(page);
            if (blogPage == null) {
                return ServerResponse.createByErrorMessage("查询失败了");
            } else {
                return ServerResponse.createBySuccess(blogPage);
            }
        }
    }

    /**
     * 通过id获取blog信息
     *
     * @param blogId  博客id
     * @param session session
     * @return ServerResponse<BlogDetailVo>
     */
    @GetMapping("/{blogId}")
    public ServerResponse<BlogDetailVo> getBlogById(@PathVariable String blogId, HttpSession session) {
        UserAccountVo userAccountVo = (UserAccountVo) session.getAttribute(Const.CURRENT_USER);
        if (userAccountVo == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        } else {
            if (blogId == null) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getDesc());
            } else {
                Blog blog = blogService.getBlogById(blogId);
                if (blog == null) {
                    return ServerResponse.createByError();
                }
                BlogItem blogItem = blogItemService.getBlogItemById(blogId);
                if (blogItem == null) {
                    return ServerResponse.createByError();
                }
                User user = userService.getUser(blog.getAuthor());
                if (user == null) {
                    return ServerResponse.createByError();
                }
                Like like = likeService.getLikeByLikeOf(blog.getBlogId());
                if (like == null) {
                    return ServerResponse.createByError();
                }
                BlogDetailVo blogDetailVo = BlogDetailVo.builder()
                        .blogId(blogId)
                        .author(user.getUsername())
                        .theme(blog.getTheme())
                        .coverImg(blog.getCoverImg())
                        .content(blogItem.getContent())
                        .createTime(blogItem.getCreateTime())
                        .likeId(like.getLikeId())
                        .likeCount(like.getLikeCount())
                        .build();
                return ServerResponse.createBySuccess(blogDetailVo);
            }
        }
    }


    // ------------------------------ 私有工具 ------------------------------- //

    /**
     * @param file    file
     * @param request request
     * @return String
     */
    private String uploadCoverImg(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        if (file != null) {
            String targetFileName = fileService.upload(file, path);
            log.info("上传博客主图成功:" + targetFileName);
            return FtpProperties.HTTP_PREFIX + targetFileName;
        } else {
            return null;
        }
    }
}
