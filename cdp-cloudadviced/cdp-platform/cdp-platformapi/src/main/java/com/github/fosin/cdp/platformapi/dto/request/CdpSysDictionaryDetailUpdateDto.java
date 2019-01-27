package com.github.fosin.cdp.platformapi.dto.request;

import java.util.Date;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统通用字典明细表(CdpSysDictionaryDetail)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 18:34:14
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典明细表更新DTO", description = "表(cdp_sys_dictionary_detail)的对应的更新DTO")
public class CdpSysDictionaryDetailUpdateDto implements Serializable {
    private static final long serialVersionUID = -77524205114983893L;
    
    @NotNull
    @ApiModelProperty(value = "字典明细ID", example = "Long", required = true)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", example = "String", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "字典明细值表示字面意义", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "字典明细值不能包含特殊字符")
    private String value;

    @NotNull
    @ApiModelProperty(value = "取值于字典明细表CdpSysDictionaryDetailEntity.code", example = "Long", required = true)
    private Long code;

    @NotNull
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", example = "Integer", required = true)
    private Integer sort;

    @NotNull
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表cdp_sys_dictionary.code=11", example = "Integer", required = true)
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "标准代码不能包含特殊字符")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @NotNull
    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", example = "Integer", required = true)
    private Integer used;

}