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

/**
 * @author ZSS
 * @date 2019/8/20 17:39
 * @description 归档实体
 */
@Entity(name = "ls_archive")
@Table(appliesTo = "ls_archive", comment = "归档表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Archive {

    /**
     * 归档id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '归档id'")
    private String archiveId;

    /**
     * 归档所属
     */
    @Column(unique = true, nullable = false, columnDefinition = "varchar(255) comment '归档所属'")
    private String archiveOf;

    /**
     * 封面图片
     */
    @Column(unique = true, nullable = false, columnDefinition = "varchar(255) comment '封面图片'")
    private String coverImg;

    /**
     * 主题
     */
    @Column(columnDefinition = "varchar(255) comment '主题'")
    private String theme;

    /**
     * 类型
     */
    @Column(nullable = false, columnDefinition = "varchar(20) comment '类型'")
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
    @Column(columnDefinition = "bigint(20) comment'更新时间'")
    private Long updateTime;
}
