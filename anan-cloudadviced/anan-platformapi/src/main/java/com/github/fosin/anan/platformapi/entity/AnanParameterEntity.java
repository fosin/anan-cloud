package com.github.fosin.anan.platformapi.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.fosin.anan.jpa.entity.AbstractSoftDeleteJpaEntity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用于存放各种分类分组的个性化参数(AnanParameter)实体类
 *
 * @author fosin
 * @date 2019-01-27 17:17:30
 * @since 1.0.0
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@SQLDelete(sql = "update anan_parameter set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
@Table(name = "anan_parameter")
@ApiModel(value = "用于存放各种分类分组的个性化参数实体类", description = "表(anan_parameter)的对应的实体类")
public class AnanParameterEntity extends AbstractSoftDeleteJpaEntity<Long, Long> {
    private static final long serialVersionUID = 301081721804164443L;

    @Basic
    @ApiModelProperty(value = "参数键", required = true)
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @ApiModelProperty(value = "参数值")
    @Column(name = "value", length = 256)
    private String value;

    @Basic
    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.id=10", required = true)
    @Column(name = "type", nullable = false)
    private Integer type;

    @Basic
    @ApiModelProperty(value = "参数作用域")
    @Column(name = "scope", length = 64)
    private String scope;

    @Basic
    @ApiModelProperty(value = "默认值")
    @Column(name = "default_value", length = 256)
    private String defaultValue;

    @Basic
    @ApiModelProperty(value = "参数描述")
    @Column(name = "description", length = 256)
    private String description;

    @Basic
    @ApiModelProperty(value = "生效日期，该值由后台维护，更改数据时前端不需要关心")
    @Column(name = "apply_time")
    private Date applyTime;

    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    @Column(name = "apply_by")
    private Long applyBy;

    @Basic
    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", required = true)
    @Column(name = "status", nullable = false)
    private Integer status = 0;

}
