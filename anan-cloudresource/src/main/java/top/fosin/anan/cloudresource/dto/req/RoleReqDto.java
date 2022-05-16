package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.OrganizIdQuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.*;

/**
 * 系统角色表(AnanRole)请求DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:18
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "角色表请求DTO", description = "角色的请求DTO")
public class RoleReqDto extends OrganizIdQuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 431913654589649616L;

    @NotBlank(message = "角色名称" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "角色名称,创建和更新时必填")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotBlank(message = "角色标识" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "角色标识,创建和更新时必填")
    @Pattern(regexp = "[\\w]{1,40}", message = "角色标识只能大小写字母、数字、下杠(_)组合而成,长度不超过40位")
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Max(value = 1, message = "使用状态" + "{javax.validation.constraints.Max.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11,创建和更新时必填")
    private Integer status;

    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是，该标志无法被设置")
    private Integer builtIn;

}
