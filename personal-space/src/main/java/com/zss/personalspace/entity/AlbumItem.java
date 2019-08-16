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
 * @date 2019/8/16 18:14
 * @description
 */
@Entity(name = "ls_album_item")
@Table(appliesTo = "ls_album_item", comment = "相册详情表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class AlbumItem {

    /**
     * 相册id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '相册id'")
    private String albumId;

    /**
     * 图片
     */
    @Column(nullable = false, columnDefinition = "text comment '图片'")
    private String photos;

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