package top.fosin.anan.platform.modules.user.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

        /**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)单体VO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)单体VO")
public class UserPermissionVO extends Id<Long> {
    private static final long serialVersionUID = -91714558750864692L;
    @Schema(description = "机构ID")
    private Long organizId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "权限ID")
    private Long permissionId;

    @Schema(description = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

}
