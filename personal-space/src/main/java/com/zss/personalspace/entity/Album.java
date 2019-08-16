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
 * @date 2019/8/16 17:32
 * @description 相册实体
 */
@Entity(name = "ls_album")
@Table(appliesTo = "ls_album", comment = "相册表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Album implements Serializable {

    /**
     * 相册id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '相册id'")
    private String albumId;

    /**
     * 发布者
     */
    @Column(updatable = false, columnDefinition = "varchar(100) comment '发布者'")
    private String publish;

    /**
     * 封面图片
     */
    @Column(columnDefinition = "varchar(255) comment '封面图片'")
    private String coverImg;

    /**
     * 主题描述
     */
    @Column(columnDefinition = "varchar(255) comment '主题描述'")
    private String theme;

    /**
     * 相册归类
     */
    @Column(nullable = false, columnDefinition = "varchar(20) comment '相册归类'")
    private String type;

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
    @Column(columnDefinition = "bigint(20) comment '更新时间'")
    private Long updateTime;
}
