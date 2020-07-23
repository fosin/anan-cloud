package com.github.fosin.anan.pojo.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统用户角色表(AnanUserRole)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 10:24:26
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统用户角色表创建DTO", description = "表(anan_user_role)的对应的创建DTO")
public class AnanUserRoleCreateDto implements Serializable {
    private static final long serialVersionUID = 749406371216875374L;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID", required = true)
    private Long organizId;

    @NotNull(message = "用户ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @NotNull(message = "角色ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

}
