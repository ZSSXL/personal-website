# 数据库设计(personal_station)

## 用户表(ls_user) 
 | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
 | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
 | 1    | user_id  | varchar |     |     |    √    |       | 用户id |
 | 2    | username | varchar |     |     |         |       | 用户名 |
 | 3    | birthday | varchar |     |     |         |       | 生日   |       
 | 4    | address  | varchar |     |     |         |       | 地址   |
 | 5    |  phone   | varchar |     |     |         |       | 电话   |
 | 6    |  email   | varchar |     |     |         |       | 邮箱   |
 | 7    |  mood    | varchar |     |     |         |       | 性情   |
 | 8    | head_img | varchar |     |     |         |       | 头像   |
 | 9    | create_time | bigint | 20 |    |         |       | 创建时间 |
 | 10   | update_time | bigint | 20 |    |         |       | 更新时间 |
 
 ## 账户表(ls_account) 
  | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
  | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
  | 1    | user_id  | varchar |     |     |    √    |       | 用户id |
  | 2    | password | varchar |     |     |         |       | 用户密码 |
  | 3    | create_time | bigint | 20 |    |         |       | 创建时间 |
  | 4    | update_time | bigint | 20 |    |         |       | 更新时间 |  
  
  ## 博客表(ls_blog) 
   | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
   | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
   | 1    | blog_id  | varchar |     |     |    √    |       | 博客id |
   | 2    | author   | varchar |     |     |         |       | 作者   |
   | 3    | cover_img| varchar |     |     |         |       | 封面图片 |       
   | 4    | theme    | varchar |     |     |         |       | 主题   |
   | 5    | create_time | bigint | 20 |    |         |       | 创建时间 |
   | 6    | update_time | bigint | 20 |    |         |       | 更新时间 | 
   
  ## 博客详情表(ls_blog_item) 
   | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
   | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
   | 1    | blog_id  | varchar |     |     |    √    |       | 博客id |
   | 2    | content  | text    |     |     |         |       | 内容 |
   | 3    | create_time | bigint | 20 |    |         |       | 创建时间 |
   | 4    | update_time | bigint | 20 |    |         |       | 更新时间 | 
 
  ## 相册表(ls_album) 
   | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
   | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
   | 1    | album_id  | varchar |     |     |    √    |       | 相册id |
   | 2    | publish   | varchar |     |     |         |       | 发布者  |
   | 3    | cover_img | varchar |     |     |         |       | 封面图片 |       
   | 4    | theme     | varchar |     |     |         |       | 相册主题 |
   | 5    | type      | varchar | 20  |     |         |       | 相册类型 |
   | 6    | create_time | bigint | 20 |     |         |       | 创建时间 |
   | 7    | update_time | bigint | 20 |     |         |       | 更新时间 |   
 
  ## 相册详情表(ls_album_item) 
   | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
   | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
   | 1    | blog_id  | varchar |     |     |    √    |       | 相册id |
   | 2    | photos   | text    |     |     |         |       | 照片   |
   | 3    | create_time | bigint | 20 |    |         |       | 创建时间 |
   | 4    | update_time | bigint | 20 |    |         |       | 更新时间 |
   
  ## 评论表(ls_comment) 
   | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
   | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
   | 1    | comment_id       | varchar |    |     |    √    |       | 评论id |
   | 2    | comment_of       | varchar |    |     |         |       | 评论所属  |
   | 3    | comment_author   | varchar |    |     |         |       | 评论作者  |       
   | 4    | head_img         | varchar |    |     |         |       |  头像     |
   | 5    | comment          | varchar |    |     |         |       |  评论     |
   | 6    | create_time | bigint | 20 |     |     |         | 创建时间 |
   | 7    | update_time | bigint | 20 |     |     |         | 更新时间 |  
   
 ## 点赞表(ls_like) 
   | 序号 | 字段  | 类型  | 长度  | 是否为空 | 是否主键 | 举例  | 描述   |
   | :---:| :---: | :---: | :---:| :---:   | :---:   | :---: | :---: |
   | 1    | like_id  | varchar |     |     |    √    |       | 点赞id |
   | 2    | like_of  | varchar |     |     |         |       | 点赞所属 |
   | 3    | like_count  | int    |    |    |         |       | 点赞数量 |
   | 4    | create_time | bigint | 20 |    |         |       | 创建时间 |
   | 5    | update_time | bigint | 20 |    |         |       | 更新时间 |