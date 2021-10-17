package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)创建DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限创建DTO", description = "用户权限的创建DTO")
public class AnanUserPermissionCreateDto implements Serializable {
    private static final long serialVersionUID = -29204374865503979L;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", required = true, example = "1")
    private Long organizId;

    @NotNull(message = "用户ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private Long userId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID", required = true, example = "1")
    private Long permissionId;

    @NotNull(message = "补充方式" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true, example = "0")
    private Integer addMode;

}
