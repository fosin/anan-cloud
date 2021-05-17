package top.fosin.anan.cloudresource.dto.req;

import top.fosin.anan.core.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用于存放各种分类分组的个性化参数(AnanParameter)创建DTO
 *
 * @author fosin
 * @date 2019-01-27 17:15:12
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于存放各种分类分组的个性化参数创建DTO", description = "表(anan_parameter)的对应的创建DTO")
public class AnanParameterCreateDto implements Serializable {
    private static final long serialVersionUID = -73747092156452855L;

    @NotBlank(message = "参数键" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "参数键", required = true)
    @Pattern(regexp = "[\\w]{1,64}", message = "参数键只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String name;

    @ApiModelProperty(value = "参数值")
    private String value;

    @NotNull(message = "参数分类" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10", required = true)
    private Integer type;

    @ApiModelProperty(value = "参数作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述")
    private String description;

    @NotNull(message = "参数状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", required = true)
    private Integer status;

}
