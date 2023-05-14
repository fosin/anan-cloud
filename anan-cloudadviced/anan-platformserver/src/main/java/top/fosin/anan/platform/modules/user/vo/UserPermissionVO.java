package top.fosin.anan.platform.modules.user.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限单体VO", description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)单体VO")
public class UserPermissionVO extends Id<Long> {
    private static final long serialVersionUID = -91714558750864692L;
    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

}
