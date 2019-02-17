package com.github.fosin.cdp.platformapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统角色权限表(CdpRolePermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统角色权限表更新DTO", description = "表(cdp_role_permission)的对应的更新DTO")
public class CdpRolePermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = -29829025776577577L;

    @NotNull
    @ApiModelProperty(value = "角色权限ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "Long", required = true)
    private Long roleId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

}
