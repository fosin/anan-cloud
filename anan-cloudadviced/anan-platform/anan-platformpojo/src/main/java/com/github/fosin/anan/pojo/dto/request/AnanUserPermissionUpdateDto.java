package com.github.fosin.anan.pojo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限更新DTO", description = "表(anan_user_permission)的对应的更新DTO")
public class AnanUserPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = -76270485644382455L;

    @NotNull(message = "用户权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "用户权限ID, 主键", example = "Long", required = true)
    private Long id;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotNull(message = "用户ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "用户ID", example = "Long", required = true)
    private Long userId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

    @NotNull(message = "补充方式" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", example = "Integer", required = true)
    private Integer addMode;

}
