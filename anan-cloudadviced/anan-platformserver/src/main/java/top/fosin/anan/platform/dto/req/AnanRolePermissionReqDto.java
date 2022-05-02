package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 系统角色权限表(AnanRolePermission)请求DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "角色权限表请求DTO", description = "角色权限的请求DTO")
public class AnanRolePermissionReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -44755376359236777L;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "角色序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "角色序号", required = true)
    private Long roleId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "权限序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限序号", required = true)
    private Long permissionId;
}
