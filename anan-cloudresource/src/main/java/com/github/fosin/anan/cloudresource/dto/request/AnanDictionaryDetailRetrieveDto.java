package com.github.fosin.anan.cloudresource.dto.request;

import com.github.fosin.anan.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典明细表(AnanDictionaryDetail)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典明细表查询DTO", description = "表(anan_dictionary_detail)的对应的查询DTO")
public class AnanDictionaryDetailRetrieveDto implements Serializable {
    private static final long serialVersionUID = 507206776709737910L;

    @ApiModelProperty(value = "字典明细ID, 主键")
    private Long id;

    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码")
    private Long name;

    @ApiModelProperty(value = "字典明细值表示字面意义")
    private String value;

    @ApiModelProperty(value = "取值于字典明细表AnanDictionaryDetailEntity.code")
    private Long dictionaryId;

    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性")
    private Integer used;
}
