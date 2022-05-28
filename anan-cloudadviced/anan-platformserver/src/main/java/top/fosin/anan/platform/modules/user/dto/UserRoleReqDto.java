package top.fosin.anan.platform.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.req.IdQuerySortOrganizDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class UserRoleReqDto extends IdQuerySortOrganizDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 818450290607468187L;

    @NotNull(message = "用户序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "用户序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "用户ID,创建和更新时必填")
    private Long userId;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "角色序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "角色ID,创建和更新时必填")
    private Long roleId;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

}
