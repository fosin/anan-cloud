package top.fosin.anan.cloudresource.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.*;

/**
 * 系统角色表(anan_role)更新DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统角色表更新DTO", description = "系统角色表(anan_role)更新DTO")
public class RoleUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = 991571316635738350L;

    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    private Long organizId;

    @NotBlank(message = "角色名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "角色名称,创建和更新时必填")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotBlank(message = "角色标识" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "角色标识,创建和更新时必填")
    @Pattern(regexp = "[\\w]{1,40}", message = "角色标识只能大小写字母、数字、下杠(_)组合而成,长度不超过40位")
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @Max(value = 1, message = "使用状态" + "{javax.validation.constraints.Max.message}")
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.id=11,创建和更新时必填")
    private Integer status;

    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是，该标志无法被设置")
    private Integer builtIn;

}
