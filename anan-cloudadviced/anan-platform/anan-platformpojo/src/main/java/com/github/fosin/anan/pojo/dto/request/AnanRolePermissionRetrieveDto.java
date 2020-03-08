package com.github.fosin.anan.pojo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色权限表(AnanRolePermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统角色权限表查询DTO", description = "表(anan_role_permission)的对应的查询DTO")
public class AnanRolePermissionRetrieveDto implements Serializable {
    private static final long serialVersionUID = -44755376359236777L;

    @ApiModelProperty(value = "角色权限ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long roleId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;
}
