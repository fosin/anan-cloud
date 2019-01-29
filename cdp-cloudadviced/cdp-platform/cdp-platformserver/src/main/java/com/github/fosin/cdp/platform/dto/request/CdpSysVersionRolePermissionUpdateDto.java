package com.github.fosin.cdp.platform.dto.request;

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
 * 系统版本角色权限表(CdpSysVersionRolePermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本角色权限表更新DTO", description = "表(cdp_sys_version_role_permission)的对应的更新DTO")
public class CdpSysVersionRolePermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = 172021255546882887L;
    
    @NotNull
    @ApiModelProperty(value = "角色权限ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "角色ID", example = "Long", required = true)
    private Long roleId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

}