package top.fosin.anan.platform.modules.organization.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 系统机构权限表(anan_organization_permission)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统机构权限表更新DTO", description = "系统机构权限表(anan_organization_permission)更新DTO")
public class OrganizationPermissionUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 564103234116768299L;

    @NotNull(message = "机构序号" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "机构序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "机构序号", required = true)
    private Long organizId;

    @NotNull(message = "权限序号" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "权限序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "权限序号", required = true)
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
