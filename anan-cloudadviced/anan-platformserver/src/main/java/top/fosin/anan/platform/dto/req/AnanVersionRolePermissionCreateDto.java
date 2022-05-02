package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "版本角色权限表创建DTO", description = "版本角色权限的创建DTO")
public class AnanVersionRolePermissionCreateDto implements Serializable {
    private static final long serialVersionUID = -75539368876091612L;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色序号", required = true)
    private Long roleId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限序号", required = true)
    private Long permissionId;

}
