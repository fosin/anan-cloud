package top.fosin.anan.platform.modules.version.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * 系统版本角色权限表(AnanVersionRolePermission)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:14:31
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "版本角色权限的请求DTO")
public class VersionRolePermissionUpdateDTO extends IdLogiSortQuery<LogiQueryRule, SortRule, Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = -42309427704059941L;

    @NotNull(message = "角色序号" + "{jakarta.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "角色序号" + "{jakarta.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "角色序号")
    private Long roleId;

    @NotNull(message = "权限序号" + "{jakarta.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "权限序号" + "{jakarta.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @Schema(description = "权限序号")
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
