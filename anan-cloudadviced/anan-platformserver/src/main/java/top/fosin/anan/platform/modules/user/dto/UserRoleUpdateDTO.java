package top.fosin.anan.platform.modules.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
/**
 * 系统用户角色表(anan_user_role)更新DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统用户角色表更新DTO", description = "系统用户角色表(anan_user_role)更新DTO")
public class UserRoleUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = -11784486133637019L;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @NotNull(message = "用户序号" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "用户序号" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "用户ID,创建和更新时必填")
    private Long userId;

    @NotNull(message = "角色序号" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "角色序号" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "角色ID,创建和更新时必填")
    private Long roleId;

    @Override
    public Long getFkValue() {
        return userId;
    }

    @Override
    public void setFkValue(Long fkValue) {
        this.userId = fkValue;
    }

    @Override
    public String getFkName() {
        return "userId";
    }
}
