package top.fosin.anan.platform.modules.organization.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotNull;
        
/**
 * 系统机构权限表(anan_organization_permission)创建DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统机构权限表(anan_organization_permission)创建DTO")
public class OrganizationPermissionCreateDTO {
    private static final long serialVersionUID = -43278167271944121L;
    
    @NotNull(message = "机构ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @NotNull(message = "权限ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Schema(description = "权限ID", example = "Long")
    private Long permissionId;

}
