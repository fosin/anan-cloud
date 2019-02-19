package com.github.fosin.cdp.platformapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统用户角色表(CdpUserRole)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 10:24:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户角色表创建DTO", description = "表(cdp_user_role)的对应的创建DTO")
public class CdpUserRoleCreateDto implements Serializable {
    private static final long serialVersionUID = 980942313454473983L;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "用户ID", example = "Long", required = true)
    private Long userId;

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "Long", required = true)
    private Long roleId;

}
