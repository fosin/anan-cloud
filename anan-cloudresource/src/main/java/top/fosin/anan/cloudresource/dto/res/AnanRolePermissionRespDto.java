package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.cloudresource.dto.PermissionDto;

/**
 * 系统角色权限表(AnanRolePermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色权限表响应DTO", description = "角色权限的响应DTO")
public class AnanRolePermissionRespDto extends PermissionDto<Long> {
    private static final long serialVersionUID = 785551683787615699L;
    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long roleId;

}
