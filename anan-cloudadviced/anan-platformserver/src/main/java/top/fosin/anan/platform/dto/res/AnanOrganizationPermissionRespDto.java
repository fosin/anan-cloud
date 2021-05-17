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
 * 系统机构权限表(AnanOrganizationPermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统机构权限表响应DTO", description = "表(anan_organization_permission)的响应DTO")
public class AnanOrganizationPermissionRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 251414814078557449L;
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

}
