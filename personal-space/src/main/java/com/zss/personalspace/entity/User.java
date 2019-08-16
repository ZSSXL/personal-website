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
 * @date 2019/8/16 17:20
 * @description 用户实体
 */
@Entity(name = "ls_user")
@Table(appliesTo = "ls_user",comment = "用户表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    /**
     * 用户id
     */
    @Id
    @Column(columnDefinition = "varchar(100) comment '用户id'")
    private String userId;

    /**
     * 用户名
     */
    @Column(nullable = false, columnDefinition = "varchar(50) comment '用户名'")
    private String username;

    /**
     * 出生年月日
     */
    @Column(columnDefinition = "varchar(50) comment '出生年月日'")
    private String birthday;

    /**
     * 现在居住地
     */
    @Column(columnDefinition = "varchar(50) comment '现在居住地'")
    private String address;

    /**
     * 电话
     */
    @Column(columnDefinition = "varchar(50) comment '电话'")
    private String phone;

    /**
     * 邮箱
     */
    @Column(columnDefinition = "varchar(50) comment '邮箱'")
    private String email;

    /**
     * 心情
     */
    @Column(columnDefinition = "varchar(50) comment '性情'")
    private String mood;

    /**
     * 头像
     */
    @Column(columnDefinition = "varchar(20) comment '头像'")
    private String headImg;

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
