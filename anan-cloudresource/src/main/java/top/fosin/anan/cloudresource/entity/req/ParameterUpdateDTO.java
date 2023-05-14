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
 * 用于存放各种分类分组的个性化参数(anan_parameter)更新DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "用于存放各种分类分组的个性化参数更新DTO", description = "用于存放各种分类分组的个性化参数(anan_parameter)更新DTO")
public class ParameterUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -55459994981170238L;

    @ApiModelProperty(value = "参数键")
    @NotBlank(message = "参数键{javax.validation.constraints.NotBlank.message}")
    @Pattern(regexp = "[\\w]{1,64}", message = "参数键只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String name;

    @ApiModelProperty(value = "参数值")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10")
    @NotNull(message = "参数分类{javax.validation.constraints.NotNull.message}")
    @Positive(message = "参数分类{javax.validation.constraints.Positive.message}")
    private Integer type;

    @ApiModelProperty(value = "参数作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述")
    private String description;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    @NotNull(message = "参数状态" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "参数状态" + "{javax.validation.constraints.Positive.message}")
    private Integer status;

}
