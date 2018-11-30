package com.github.fosin.cdp.platformapi.entity;

import java.util.Date;

import com.github.fosin.cdp.mvc.constant.RegExpConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;
/**
 * 系统版本角色表(CdpSysVersionRole)实体类
 *
 * @author fosin
 * @date 2018-11-18 17:28:24
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_version_role")
@ApiModel(value = "系统版本角色表实体类", description = "表(cdp_sys_version_role)的对应的实体类")
public class CdpSysVersionRoleEntity implements Serializable {
    private static final long serialVersionUID = -75498558716473872L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "主键，系统自动生成,角色ID")
    private Long id;
    
    @Basic
    @NotNull
    @Column(name = "version_id")
    @ApiModelProperty(value = "版本ID")
    private Long versionId;
    
    @Basic
    @NotBlank
    @Column(name = "name")
    @ApiModelProperty(value = "角色名称")
    @Pattern(regexp = RegExpConstant.SPECIAL, message = "名称不能包含特殊字符")
    private String name;
    
    @Basic
    @NotBlank
    @Column(name = "value")
    @ApiModelProperty(value = "角色标识")
    @Pattern(regexp = RegExpConstant.USERCODE + "{1,40}", message = "角色标识只能大小写字母、数字、下杠(_)、中杠(-)组合而成,长度不超过40位")
    private String value;
    
    @Basic
    @Column(name = "tips")
    @ApiModelProperty(value = "角色说明")
    private String tips;
    
    @Basic
    @NotNull
    @Column(name = "status")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    
    @Basic
    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;
    
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    @Basic
    @Column(name = "update_by")
    @ApiModelProperty(value = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;
    
}