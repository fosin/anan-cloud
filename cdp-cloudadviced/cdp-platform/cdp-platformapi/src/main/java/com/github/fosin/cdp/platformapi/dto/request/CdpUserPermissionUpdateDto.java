package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(CdpUserPermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限更新DTO", description = "表(cdp_user_permission)的对应的更新DTO")
public class CdpUserPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = -22032968753010923L;

    @NotNull
    @ApiModelProperty(value = "用户权限ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "用户ID", example = "Long", required = true)
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

    @NotNull
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", example = "Integer", required = true)
    private Integer addMode;

}
