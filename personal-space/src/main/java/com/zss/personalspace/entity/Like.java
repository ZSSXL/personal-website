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
 * @date 2019/8/16 18:03
 * @description 点赞实体
 */
@Entity(name = "ls_like")
@Table(appliesTo = "ls_like", comment = "博客表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Like implements Serializable {

    /**
     * 点赞id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '点赞id'")
    private String likeId;

    /**
     * 点赞所属
     */
    @Column(nullable = false, columnDefinition = "varchar(100) comment '点赞所属，给什么点赞'")
    private String likeOf;

    /**
     * 点赞数量
     */
    @Column(columnDefinition = "int(8) comment '点赞数量'")
    private Integer likeCount;

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
