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

import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

/**
 * 系统版本权限表(AnanVersionPermission)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "版本权限表请求DTO", description = "版本权限的请求DTO")
public class AnanVersionPermissionReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 425131909775170449L;

    @NotNull(message = "版本序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "版本序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "版本序号", required = true)
    private Long versionId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "权限序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限序号", required = true)
    private Long permissionId;

}
