package com.zss.richtextformatdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author ZSS
 * @date 2019/8/10 21:47
 * @description 富文本存储实体
 */
@Entity
@Table(name = "t_rich_text")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RichText {

    @Id
    @Column(columnDefinition = "varchar(50)")
    private String id;

    @Column(columnDefinition = "text comment '符文编辑器内容'")
    private String rich;

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
    @Column(columnDefinition = "bigint(20) comment '修改日期'")
    private Long updateTime;
}
