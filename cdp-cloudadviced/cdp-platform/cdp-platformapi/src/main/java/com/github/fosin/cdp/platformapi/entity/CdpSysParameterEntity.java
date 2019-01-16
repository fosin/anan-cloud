package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.jpa.entity.AbstractCreateUpdateJpaEntity;
import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 用于存放各种分类分组的个性化参数(CdpSysParameter)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_parameter")
@ApiModel(value = "用于存放各种分类分组的个性化参数实体类", description = "表(cdp_sys_parameter)的对应的实体类")
public class CdpSysParameterEntity extends AbstractCreateUpdateJpaEntity implements Serializable {
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
    @Pattern(regexp = "[\\w]{1,64}", message = "参数键只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
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
    @Pattern(regexp =RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;
    
    @Column(name = "default_value")
    @Basic
    @ApiModelProperty(value = "默认值", notes = "默认值")
    private String defaultValue;
    
    @Column(name = "description")
    @Basic
    @ApiModelProperty(value = "参数描述", notes = "参数描述")
    private String description;

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