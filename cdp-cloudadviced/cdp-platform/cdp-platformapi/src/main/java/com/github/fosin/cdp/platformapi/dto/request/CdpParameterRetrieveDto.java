package com.github.fosin.cdp.platformapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用于存放各种分类分组的个性化参数(CdpParameter)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:17
 * @since 1.0.0
 */
@Data
@ApiModel(value = "用于存放各种分类分组的个性化参数查询DTO", description = "表(cdp_parameter)的对应的查询DTO")
public class CdpParameterRetrieveDto implements Serializable {
    private static final long serialVersionUID = -95372770044687456L;

    @ApiModelProperty(value = "参数ID, 主键", example = "Long")
    private Long id;

    @ApiModelProperty(value = "参数键", example = "String")
    @NotBlank(message = "参数键{org.hibernate.validator.constraints.NotBlank.message}")
    private String name;

    @ApiModelProperty(value = "参数值", example = "String")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表cdp_dictionary.code=10", example = "Integer")
    @NotNull(message = "参数分类{javax.validation.constraints.NotNull.message}")
    private Integer type;

    @ApiModelProperty(value = "参数作用域", example = "String")
    private String scope;

    @ApiModelProperty(value = "默认值", example = "String")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述", example = "String")
    private String description;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", example = "Integer")
    private Integer status;

}
