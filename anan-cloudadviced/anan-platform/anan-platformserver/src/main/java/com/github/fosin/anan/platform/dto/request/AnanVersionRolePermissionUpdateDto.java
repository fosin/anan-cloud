package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本角色权限表更新DTO", description = "表(anan_version_role_permission)的对应的更新DTO")
public class AnanVersionRolePermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = -12592204733930893L;

    @NotNull(message = "角色权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色权限ID, 主键", example = "Long", required = true)
    private Long id;

    @NotNull(message = "角色ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色ID", example = "Long", required = true)
    private Long roleId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

}
