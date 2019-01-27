package com.github.fosin.cdp.platformapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统角色权限表(CdpSysRolePermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统角色权限表查询DTO", description = "表(cdp_sys_role_permission)的对应的查询DTO")
public class CdpSysRolePermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = 204949542973755248L;

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long roleId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;
}