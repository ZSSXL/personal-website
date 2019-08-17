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
 * @date 2019/8/16 17:30
 * @description 账户实体
 */
@Entity(name = "ls_account")
@Table(appliesTo = "ls_account", comment = "账户表")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Account implements Serializable {

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
     * 用户密码
     */
    @Column(nullable = false, columnDefinition = "varchar(255) comment '用户密码'")
    private String password;

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
