package top.fosin.anan.platform.modules.organization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 系统机构权限表(AnanOrganizationPermission)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "机构权限表请求DTO", description = "机构权限的请求DTO")
public class OrgPermissionReqDto extends IdLogiSortQuery<LogiQueryRule, SortRule, Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 258891966081450196L;

    @NotNull(message = "机构序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "机构序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "机构序号", required = true)
    private Long organizId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "权限序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限序号", required = true)
    private Long permissionId;

    @Override
    public Long getFkValue() {
        return organizId;
    }

    @Override
    public void setFkValue(Long foreingKey) {
        this.organizId = foreingKey;
    }

    @Override
    public String getFkName() {
        return "organizId";
    }
}
