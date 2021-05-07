package top.fosin.anan.cloudresource.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.module.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色权限表(AnanRolePermission)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统角色权限表查询DTO", description = "表(anan_role_permission)的对应的查询DTO")
public class AnanRolePermissionRetrieveDto extends QuerySortRuleDto<QueryRule,SortRule> implements Serializable {
    private static final long serialVersionUID = -44755376359236777L;

    @ApiModelProperty(value = "角色权限ID, 主键")
    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}
