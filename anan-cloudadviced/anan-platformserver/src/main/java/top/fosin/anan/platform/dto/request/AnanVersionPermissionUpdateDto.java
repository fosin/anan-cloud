package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 * 系统版本权限表(AnanVersionPermission)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本权限表更新DTO", description = "表(anan_version_permission)的对应的更新DTO")
public class AnanVersionPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = 153082865898702514L;

    @NotNull(message = "版本权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本权限ID, 主键", required = true)
    private Long id;

    @NotNull(message = "版本ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "版本ID", required = true)
    private Long versionId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID", required = true)
    private Long permissionId;

}
