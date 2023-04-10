package top.fosin.anan.platform.modules.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortOrganizQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 系统角色用户表(AnanUserRole)请求DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "角色用户表请求DTO", description = "角色用户的请求DTO")
public class RoleUserReqDto extends IdLogiSortOrganizQuery<LogiQueryRule, SortRule, Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 818450290607468187L;

    @NotNull(message = "用户序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "用户序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "用户ID,创建和更新时必填")
    private Long userId;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "角色序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "角色ID,创建和更新时必填")
    private Long roleId;

    @ApiModelProperty(value = "该值由后台维护，更改数据时前端不需要关心，取值于anan_user.id")
    private Long createBy;

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
