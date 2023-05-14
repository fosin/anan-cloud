package top.fosin.anan.platform.modules.version.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.cloudresource.entity.PermissionId;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "版本角色权限表响应DTO", description = "版本角色权限的响应DTO")
public class VersionRolePermissionDTO extends PermissionId<Long> {
    private static final long serialVersionUID = -72983140389430793L;
    @ApiModelProperty(value = "角色序号", example = "Long")
    private Long roleId;

}
