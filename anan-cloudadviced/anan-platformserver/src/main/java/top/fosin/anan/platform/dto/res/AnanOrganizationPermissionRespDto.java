package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

import java.io.Serializable;

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
@ApiModel(value = "机构权限表响应DTO", description = "机构权限的响应DTO")
public class AnanOrganizationPermissionRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 251414814078557449L;
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

}
