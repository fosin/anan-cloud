package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(CdpSysUserPermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限查询DTO", description = "表(cdp_sys_user_permission)的对应的查询DTO")
public class CdpSysUserPermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = -34361883488483228L;
    
    @NotNull
    @ApiModelProperty(value = "用户权限ID", example = "Long")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "用户ID", example = "Long")
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "Date")
    private Date createTime;

    @NotNull
    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于cdp_sys_user.id", example = "Long")
    private Long createBy;

    @NotNull
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", example = "Integer")
    private Integer addMode;

}