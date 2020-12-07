package com.github.fosin.anan.platform.entity;

import com.github.fosin.anan.jpa.entity.AbstractCreateUpdateJpaEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 国际化语言集(AnanInternational)实体类
 *
 * @author fosin
 * @date 2020-12-05 17:39:31
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "anan_international")
@ApiModel(value = "表(anan_international)的对应的实体类", description = "表(anan_international)的对应的实体类")
public class AnanInternationalEntity extends AbstractCreateUpdateJpaEntity<Long, Integer> implements Serializable {
    private static final long serialVersionUID = -36988953020042426L;

    @Basic
    @Column(name = "code")
    @ApiModelProperty(value = "国际化通用标识")
    private String code;

    @Basic
    @Column(name = "name")
    @ApiModelProperty(value = "国际化名称")
    private String name;

    @Basic
    @Column(name = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

    @Basic
    @Column(name = "status")
    @ApiModelProperty(value = "状态：0=启用，1=禁用")
    private Integer status;

    @Basic
    @Column(name = "default_flag")
    @ApiModelProperty(value = "默认标志")
    private Integer defaultFlag;

}
