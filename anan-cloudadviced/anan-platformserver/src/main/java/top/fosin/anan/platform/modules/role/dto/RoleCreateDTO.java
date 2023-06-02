package top.fosin.anan.platform.modules.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.*;

/**
 * 系统角色表(anan_role)创建DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "系统角色表创建DTO", description = "系统角色表(anan_role)创建DTO")
public class RoleCreateDTO {
    private static final long serialVersionUID = -87792271649200164L;

    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    private Long organizId;

    @NotBlank(message = "角色名称" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "角色名称,创建和更新时必填")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Create.class)
    private String name;

    @NotBlank(message = "角色标识" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "角色标识，创建和更新时必填，角色标识只能大写字母开始，和数字、下杠(_)组合而成,长度2-40位")
    @Pattern(regexp = "^[A-Z][A-Z0-9_]{2,40}", message = "角色标识只能大写字母开始，和数字、下杠(_)组合而成,长度2-40位", groups = Create.class)
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @Min(value = 0, message = "使用状态" + "{javax.validation.constraints.Min.message}", groups = Create.class)
    @Max(value = 1, message = "使用状态" + "{javax.validation.constraints.Max.message}", groups = Create.class)
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11,创建和更新时必填")
    private Byte status;

}
