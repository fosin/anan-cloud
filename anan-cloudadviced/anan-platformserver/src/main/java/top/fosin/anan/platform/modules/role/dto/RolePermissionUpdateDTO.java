package top.fosin.anan.platform.modules.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 系统角色权限表(anan_role_permission)更新DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统角色权限表更新DTO", description = "系统角色权限表(anan_role_permission)更新DTO")
public class RolePermissionUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 175264508207739660L;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "角色序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "角色序号", required = true)
    private Long roleId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "权限序号" + "{javax.validation.constraints.Positive.message}")
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
