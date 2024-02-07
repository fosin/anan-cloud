package top.fosin.anan.platform.modules.organization.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * 系统机构权限表(anan_organization_permission)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统机构权限表(anan_organization_permission)更新DTO")
public class OrganizationPermissionUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 564103234116768299L;

    @NotNull(message = "机构序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "机构序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "机构序号")
    private Long organizId;

    @NotNull(message = "权限序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "权限序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "权限序号")
    private Long permissionId;

    @Override
    public Long getFkValue() {
        return organizId;
    }

    @Override
    public void setFkValue(Long fkValue) {
        this.organizId = fkValue;
    }

    @Override
    public String getFkName() {
        return "organizId";
    }
}
