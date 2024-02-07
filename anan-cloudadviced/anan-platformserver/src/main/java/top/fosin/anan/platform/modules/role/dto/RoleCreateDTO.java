package top.fosin.anan.platform.modules.role.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.*;

/**
 * 系统角色表(anan_role)创建DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "系统角色表(anan_role)创建DTO")
public class RoleCreateDTO {
    private static final long serialVersionUID = -87792271649200164L;

    @Schema(description = "机构ID", example = "Long")
    @NotNull(message = "机构ID" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    private Long organizId;

    @NotBlank(message = "角色名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "角色名称,创建和更新时必填")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Create.class)
    private String name;

    @NotBlank(message = "角色标识" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "角色标识，创建和更新时必填，角色标识只能大写字母开始，和数字、下杠(_)组合而成,长度2-40位")
    @Pattern(regexp = "^[A-Z][A-Z0-9_]{2,40}", message = "角色标识只能大写字母开始，和数字、下杠(_)组合而成,长度2-40位", groups = Create.class)
    private String value;

    @Schema(description = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Min(value = 0, message = "使用状态" + "{jakarta.validation.constraints.Min.message}", groups = Create.class)
    @Max(value = 1, message = "使用状态" + "{jakarta.validation.constraints.Max.message}", groups = Create.class)
    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11,创建和更新时必填")
    private Byte status;

}
