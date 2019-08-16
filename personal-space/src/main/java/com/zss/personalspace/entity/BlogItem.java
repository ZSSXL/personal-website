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
 * @date 2019/8/16 18:13
 * @description
 */
@Entity(name = "ls_blog_item")
@Table(appliesTo = "ls_blog_item", comment = "博客详情表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class BlogItem implements Serializable {

    /**
     * 博客id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '博客id'")
    private String blogId;

    /**
     * 博客内容
     */
    @Column(nullable = false, columnDefinition = "text comment '博客内容'")
    private String content;

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
