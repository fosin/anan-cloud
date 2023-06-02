package top.fosin.anan.platform.modules.organization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotNull;
        
/**
 * 系统机构权限表(anan_organization_permission)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "系统机构权限表创建DTO", description = "系统机构权限表(anan_organization_permission)创建DTO")
public class OrganizationPermissionCreateDTO {
    private static final long serialVersionUID = -43278167271944121L;
    
    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @NotNull(message = "权限ID" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @ApiModelProperty(value = "权限ID", required = true, example = "Long")
    private Long permissionId;

}
