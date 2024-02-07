package top.fosin.anan.platform.modules.role.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortOrganizIdQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
/**
 * 系统角色用户表(AnanUserRole)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色用户的请求DTO")
public class RoleUserUpdateDTO extends IdLogiSortOrganizIdQuery<LogiQueryRule, SortRule, Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 818450290607468187L;

    @NotNull(message = "用户序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "用户序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "用户ID,创建和更新时必填")
    private Long userId;

    @NotNull(message = "角色序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "角色序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "角色ID,创建和更新时必填")
    private Long roleId;
    
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
