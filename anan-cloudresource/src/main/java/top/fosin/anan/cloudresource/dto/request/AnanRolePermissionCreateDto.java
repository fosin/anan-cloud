package top.fosin.anan.cloudresource.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统角色权限表(AnanRolePermission)创建DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统角色权限表创建DTO", description = "表(anan_role_permission)的对应的创建DTO")
public class AnanRolePermissionCreateDto implements Serializable {
    private static final long serialVersionUID = -15682978445746756L;

    @NotNull(message = "角色ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID", required = true)
    private Long permissionId;

}
