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
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)请求DTO
 *
 * @author fosin
 * @date 2019-01-27 19:33:26
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限请求DTO", description = "用户权限的请求DTO")
public class AnanUserPermissionReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 989390435758584592L;

    @NotNull(message = "机构序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "机构序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "机构序号")
    private Long organizId;

    @NotNull(message = "用户序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "用户序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "用户序号")
    private Long userId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "权限序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限序号")
    private Long permissionId;

    @NotNull(message = "补充方式" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 0, message = "补充方式" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

}
