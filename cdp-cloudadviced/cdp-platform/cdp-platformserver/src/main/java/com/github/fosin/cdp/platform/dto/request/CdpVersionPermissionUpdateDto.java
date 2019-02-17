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
 * 系统版本权限表(CdpVersionPermission)更新DTO
 *
 * @author fosin
 * @date 2019-01-28 11:45:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统版本权限表更新DTO", description = "表(cdp_version_permission)的对应的更新DTO")
public class CdpVersionPermissionUpdateDto implements Serializable {
    private static final long serialVersionUID = -28904428270017930L;

    @NotNull
    @ApiModelProperty(value = "版本权限ID", example = "Long", required = true)
    private Long id;

    @NotNull
    @ApiModelProperty(value = "版本ID", example = "Long", required = true)
    private Long versionId;

    @NotNull
    @ApiModelProperty(value = "权限ID", example = "Long", required = true)
    private Long permissionId;

}
