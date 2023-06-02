package top.fosin.anan.platform.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotNull;
        
/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)创建DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限创建DTO", description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)创建DTO")
public class UserPermissionCreateDTO {
    private static final long serialVersionUID = -35387434959277339L;
    
    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @NotNull(message = "用户ID" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "用户ID", required = true, example = "Long")
    private Long userId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "权限ID", required = true, example = "Long")
    private Long permissionId;

    @NotNull(message = "补充方式：0=增加权限、1=删除权限" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true, example = "Integer")
    private Integer addMode;

}
