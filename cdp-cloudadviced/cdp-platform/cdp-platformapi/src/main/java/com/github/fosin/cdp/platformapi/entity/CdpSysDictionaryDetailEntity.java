package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;

import com.github.fosin.cdp.util.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 通用系统字典明细表(CdpSysDictionaryDetail)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_dictionary_detail")
@ApiModel(value = "通用系统字典明细表实体类", description = "表(cdp_sys_dictionary_detail)的对应的实体类")
public class CdpSysDictionaryDetailEntity implements Serializable {
    private static final long serialVersionUID = -54584625890981795L;
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "字典明细ID", notes = "主键，系统自动生成,字典明细ID")
    private Long id;
    
    @Column(name = "name")
    @Basic
    @NotNull
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", notes = "字典明细键，不能重复，字典内明细项唯一代码")
    private Long name;
    
    @Column(name = "value")
    @Basic
    @ApiModelProperty(value = "字典明细值表示字面意义", notes = "字典明细值表示字面意义")
    private String value;
    
    @Column(name = "code")
    @Basic
    @NotNull
    @ApiModelProperty(value = "取值于字典明细表CdpSysDictionaryDetailEntity.code", notes = "取值于字典明细表CdpSysDictionaryDetailEntity.code")
    private Long code;
    
    @Column(name = "sort")
    @Basic
    @NotNull
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", notes = "顺序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;
    
    @Column(name = "status")
    @Basic
    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", notes = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;
    
    @Column(name = "scode")
    @Basic
    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典", notes = "标准代码，该字段通常用于对接标准字典")
    private String scode;
    
    @Column(name = "scope")
    @Basic
    @ApiModelProperty(value = "作用域，用于字典明细项的作用域", notes = "作用域，用于字典明细项的作用域")
    private String scope;
    
    @Column(name = "create_by")
    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
    @Column(name = "create_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", notes = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;
    
    @Column(name = "update_by")
    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;
    
    @Column(name = "update_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", notes = "更新日期，该值由后台维护，更改数据时前端不需要关心")
    private Date updateTime;
    
}