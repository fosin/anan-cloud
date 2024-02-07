package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.*;

/**
 * 系统角色表(anan_role)更新DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统角色表(anan_role)更新DTO")
public class RoleAuthDTO extends Id<Long> {
    private static final long serialVersionUID = 991571316635738350L;

    @NotBlank(message = "角色名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "角色名称,创建和更新时必填")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Update.class)
    private String name;

    @NotBlank(message = "角色标识" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "角色标识,创建和更新时必填")
    @Pattern(regexp = "[\\w]{1,40}", message = "角色标识只能大小写字母、数字、下杠(_)组合而成,长度不超过40位", groups = Update.class)
    private String value;
}
