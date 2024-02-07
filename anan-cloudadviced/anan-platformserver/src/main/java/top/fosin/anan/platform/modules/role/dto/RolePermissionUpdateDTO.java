package top.fosin.anan.platform.modules.role.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
/**
 * 系统角色权限表(anan_role_permission)更新DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统角色权限表(anan_role_permission)更新DTO")
public class RolePermissionUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 175264508207739660L;

    @NotNull(message = "角色序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "角色序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "角色序号")
    private Long roleId;

    @NotNull(message = "权限序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "权限序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
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
