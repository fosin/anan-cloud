package com.github.fosin.cdp.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统机构权限表(CdpOrganizationPermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构权限表更新DTO", description = "表(cdp_organization_permission)的对应的更新DTO")
public class CdpOrganizationPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = 537024184825335591L;

    @NotNull
    @ApiModelProperty(value = "机构权限ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "Long", required = true)
    private Long organizId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

}
