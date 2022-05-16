package top.fosin.anan.platform.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.cloudresource.dto.PermissionDto;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限响应DTO", description = "用户权限的响应DTO")
public class UserPermissionRespDto extends PermissionDto<Long> {
    private static final long serialVersionUID = -28245693115711034L;
    @ApiModelProperty(value = "机构序号", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户序号", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", example = "Integer")
    private Integer addMode;

}
