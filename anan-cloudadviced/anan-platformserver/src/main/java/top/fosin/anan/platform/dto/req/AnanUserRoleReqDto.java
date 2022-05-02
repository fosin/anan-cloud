package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

import javax.validation.constraints.NotNull;

/**
 * 系统用户角色表(AnanUserRole)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用户角色表请求DTO", description = "用户角色的请求DTO")
public class AnanUserRoleReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 818450290607468187L;

    @NotNull(message = "机构序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID,创建和更新时必填")
    private Long organizId;

    @NotNull(message = "用户序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "用户ID,创建和更新时必填")
    private Long userId;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "角色ID,创建和更新时必填")
    private Long roleId;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

}
