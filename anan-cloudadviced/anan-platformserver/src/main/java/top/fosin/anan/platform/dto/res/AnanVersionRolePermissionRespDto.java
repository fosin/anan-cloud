package top.fosin.anan.platform.dto.res;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统版本角色权限表响应DTO", description = "表(anan_version_role_permission)的响应DTO")
public class AnanVersionRolePermissionRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = -72983140389430793L;
    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long roleId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

}
