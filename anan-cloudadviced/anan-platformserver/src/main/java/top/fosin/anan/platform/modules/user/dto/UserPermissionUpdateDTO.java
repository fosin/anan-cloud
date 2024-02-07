package top.fosin.anan.platform.modules.user.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)更新DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)更新DTO")
public class UserPermissionUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = -77740212969406976L;
    
    @NotNull(message = "机构ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @NotNull(message = "用户序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "用户序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "用户序号")
    private Long userId;

    @NotNull(message = "权限序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "权限序号" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "权限序号")
    private Long permissionId;

    @NotNull(message = "补充方式" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "补充方式" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "补充方式：0=增加权限、1=删除权限")
    private Integer addMode;

    @Override
    public Long getFkValue() {
        return userId;
    }

    @Override
    public void setFkValue(Long foreingKey) {
        this.userId = foreingKey;
    }

    @Override
    public String getFkName() {
        return "userId";
    }
}
