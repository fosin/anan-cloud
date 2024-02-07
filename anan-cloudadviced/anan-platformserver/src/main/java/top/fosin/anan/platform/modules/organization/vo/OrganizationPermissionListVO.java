package top.fosin.anan.platform.modules.organization.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统机构权限表(anan_organization_permission)集合VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统机构权限表(anan_organization_permission)集合VO")
public class OrganizationPermissionListVO extends Id<Long> {
    private static final long serialVersionUID = 595146785708366567L;
    @Schema(description = "机构ID")
    private Long organizId;

    @Schema(description = "权限ID")
    private Long permissionId;

}
