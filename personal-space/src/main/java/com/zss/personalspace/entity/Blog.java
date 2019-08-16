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
 * @date 2019/8/16 17:40
 * @description 博客实体
 */
@Entity(name = "ls_blog")
@Table(appliesTo = "ls_blog", comment = "博客表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Blog implements Serializable {

    /**
     * 博客id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '博客id'")
    private String blogId;

    /**
     * 作者
     */
    @Column(nullable = false, columnDefinition = "varchar(100) comment '作者'")
    private String author;

    /**
     * 封面图片
     */
    @Column(columnDefinition = "varchar(255) comment '封面图片'")
    private String coverImg;

    /**
     * 博客主题
     */
    @Column(nullable = false, columnDefinition = "varchar(255) comment '博客主题'")
    private String theme;

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
