package top.fosin.anan.platform.modules.organization.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统机构权限表(anan_organization_permission)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统机构权限表单体VO", description = "系统机构权限表(anan_organization_permission)单体VO")
public class OrganizationPermissionVO extends Id<Long> {
    private static final long serialVersionUID = 788249276682585355L;
    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}
