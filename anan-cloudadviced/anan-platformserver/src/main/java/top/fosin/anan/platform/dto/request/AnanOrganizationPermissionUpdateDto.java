package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统机构权限表(AnanOrganizationPermission)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构权限表更新DTO", description = "表(anan_organization_permission)的对应的更新DTO")
public class AnanOrganizationPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = 186440811228040786L;

    @NotNull(message = "机构权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构权限ID, 主键", required = true)
    private Long id;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", required = true)
    private Long organizId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID", required = true)
    private Long permissionId;

}
