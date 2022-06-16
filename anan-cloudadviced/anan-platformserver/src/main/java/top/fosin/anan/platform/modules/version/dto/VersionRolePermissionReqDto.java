package top.fosin.anan.platform.modules.version.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.req.IdQuerySortDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.prop.ForeignKeyProp;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "版本角色权限表请求DTO", description = "版本角色权限的请求DTO")
public class VersionRolePermissionReqDto extends IdQuerySortDto<LogicalQueryRule, SortRule, Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = -42309427704059941L;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "角色序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "角色序号", required = true)
    private Long roleId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "权限序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "权限序号", required = true)
    private Long permissionId;

    @Override
    public Long getFkValue() {
        return roleId;
    }

    @Override
    public void setFkValue(Long foreingKey) {
        this.roleId = foreingKey;
    }

    @Override
    public String getFkName() {
        return "roleId";
    }
}
