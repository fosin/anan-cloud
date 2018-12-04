package com.github.fosin.cdp.platformapi.entity;

import com.github.fosin.cdp.util.DateTimeUtil;
import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色表(CdpSysRole)实体类
 *
 * @author fosin
 * @date 2018-10-27 09:38:39
 * @since 1.0.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "cdp_sys_role")
@ApiModel(value = "系统角色表实体类", description = "表(cdp_sys_role)的对应的实体类")
public class CdpSysRoleEntity implements Serializable {
    private static final long serialVersionUID = 771122304639044684L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "角色ID", notes = "主键，系统自动生成,角色ID")
    private Long id;

    @Column(name = "name")
    @Basic
    @NotBlank
    @ApiModelProperty(value = "角色名称", notes = "角色名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @Column(name = "organiz_id")
    @Basic
    @NotNull
    @ApiModelProperty(value = "机构ID", notes = "机构ID")
    private Long organizId;


    @Column(name = "value")
    @Basic
    @Pattern(regexp = RegexUtil.USERCODE + "{1,40}", message = "角色标识只能大小写字母、数字、下杠(_)组合而成,长度不超过40位")
    @ApiModelProperty(value = "角色标识", notes = "角色标识")

    private String value;

    @Column(name = "tips")
    @Basic
    @ApiModelProperty(value = "角色说明", notes = "角色说明")
    private String tips;

    @Column(name = "status")
    @Basic
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", notes = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11")
    private Integer status;

    @Column(name = "create_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    private Date createTime;

    @Column(name = "create_by")
    @Basic
    @ApiModelProperty(value = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "创建人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long createBy;

    @Column(name = "update_time")
    @Basic
    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "更新时间", notes = "更新时间")
    private Date updateTime;

    @Column(name = "update_by")
    @Basic
    @ApiModelProperty(value = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", notes = "更新人，该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id")
    private Long updateBy;

    @Column(name = "built_in")
    @Basic
    @NotNull
    @Range(max = 1)
    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是")
    private Integer builtIn;

}