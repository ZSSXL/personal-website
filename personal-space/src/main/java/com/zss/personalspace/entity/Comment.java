package com.zss.personalspace.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ZSS
 * @date 2019/8/16 17:48
 * @description 评论实体
 */
@Entity(name = "ls_comment")
@Table(appliesTo = "ls_comment",comment = "评论表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Serializable {

    /**
     * 评论id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '评论id'")
    private String commentId;

    /**
     * 评论所属
     */
    @Column(nullable = false, columnDefinition = "varchar(100) comment '评论所属'")
    private String commentOf;

    /**
     * 评论者
     */
    @Column(nullable = false, columnDefinition = "varchar(50) comment '评论者（姓名非id）' ")
    private String commentAuthor;

    /**
     * 作者头像
     */
    @Column(columnDefinition = "varchar(255) comment '作者头像'")
    private String headImg;

    /**
     * 评论内容
     */
    @Column(nullable = false, columnDefinition = "varchar(255) comment '评论内容'")
    private String comment;

    /**
     * 创建日期
     */
    @Column(updatable = false, columnDefinition = "bigint(20) comment '创建日期'")
    @CreatedDate
    private Long createTime;
    /**
     * 修改日期
     */
    @LastModifiedDate
    @Column(columnDefinition = "bigint(20) comment'更新时间'")
    private Long updateTime;
}

