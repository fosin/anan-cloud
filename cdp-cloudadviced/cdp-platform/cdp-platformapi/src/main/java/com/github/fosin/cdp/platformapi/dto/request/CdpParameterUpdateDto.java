package com.github.fosin.cdp.platformapi.dto.request;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用于存放各种分类分组的个性化参数(CdpParameter)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 17:15:12
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于存放各种分类分组的个性化参数更新DTO", description = "表(cdp_parameter)的对应的更新DTO")
public class CdpParameterUpdateDto implements Serializable {
    private static final long serialVersionUID = -66906700665921741L;

    @NotNull(message = "参数ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "参数ID, 主键", example = "Long", required = true)
    private Long id;

    @NotBlank(message = "参数键" + "{org.hibernate.validator.constraints.NotBlank.message}")
    @ApiModelProperty(value = "参数键", example = "String", required = true)
    @Pattern(regexp = "[\\w]{1,64}", message = "参数键只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String name;

    @ApiModelProperty(value = "参数值", example = "String")
    private String value;

    @NotNull(message = "参数分类" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "参数分类：具体取值于字典表cdp_dictionary.code=10", example = "Integer", required = true)
    private Integer type;

    @ApiModelProperty(value = "参数作用域", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "默认值", example = "String")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述", example = "String")
    private String description;

    @NotNull(message = "参数状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", example = "Integer", required = true)
    private Integer status;

}
