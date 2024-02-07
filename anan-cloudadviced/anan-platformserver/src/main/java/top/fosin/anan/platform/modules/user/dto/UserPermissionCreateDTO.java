package top.fosin.anan.platform.modules.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotNull;
        
/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)创建DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)创建DTO")
public class UserPermissionCreateDTO {
    private static final long serialVersionUID = -35387434959277339L;
    
    @NotNull(message = "机构ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @NotNull(message = "用户ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "用户ID", example = "Long")
    private Long userId;

    @NotNull(message = "权限ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "权限ID", example = "Long")
    private Long permissionId;

    @NotNull(message = "补充方式：0=增加权限、1=删除权限" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "补充方式：0=增加权限、1=删除权限", example = "Integer")
    private Integer addMode;

}
