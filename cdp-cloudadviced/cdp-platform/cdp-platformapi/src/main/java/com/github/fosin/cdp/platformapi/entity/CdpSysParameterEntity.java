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
 * 用于存放各种分类分组的个性化参数(CdpSysParameter)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_parameter")
@ApiModel(value = "用于存放各种分类分组的个性化参数实体类", description = "表(cdp_sys_parameter)的对应的实体类")
public class CdpSysParameterEntity implements Serializable {
    private static final long serialVersionUID = -98889841142479554L;
    
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "参数ID", notes = "主键，系统自动生成,参数ID")
    private Long id;
    
    @Column(name = "name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "参数键", notes = "参数键")
    private String name;
    
    @Column(name = "value")
    @Basic
    @ApiModelProperty(value = "参数值", notes = "参数值")
    private String value;
    
    @Column(name = "type")
    @Basic
    @NotNull
    @ApiModelProperty(value = "参数分类：具体取值于字典表cdp_sys_dictionary.code=10", notes = "参数分类：具体取值于字典表cdp_sys_dictionary.code=10")
    private Integer type;
    
    @Column(name = "scope")
    @Basic
    @ApiModelProperty(value = "参数作用域", notes = "参数作用域")
    private String scope;
    
    @Column(name = "default_value")
    @Basic
    @ApiModelProperty(value = "默认值", notes = "默认值")
    private String defaultValue;
    
    @Column(name = "description")
    @Basic
    @ApiModelProperty(value = "参数描述", notes = "参数描述")
    private String description;
    
    @Column(name = "create_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期，该值由后台维护，更改数据时前端不需要关心", notes = "创建日期，该值由后台维护，更改数据时前端不需要关心")
    private Date createTime;
    
    @Column(name = "create_by")
    @Basic
    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
    @Column(name = "update_time")
    @Basic
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新日期，该值由后台维护，更改数据时前端不需要关心", notes = "更新日期，该值由后台维护，更改数据时前端不需要关心")
    private Date updateTime;
    
    @Column(name = "update_by")
    @Basic
    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;
    
    @Column(name = "apply_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生效日期，该值由后台维护，更改数据时前端不需要关心", notes = "生效日期，该值由后台维护，更改数据时前端不需要关心")
    private Date applyTime;
    
    @Column(name = "apply_by")
    @Basic
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long applyBy;
    
    @Column(name = "status")
    @Basic
    @NotNull
    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", notes = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    private Integer status;
    
}