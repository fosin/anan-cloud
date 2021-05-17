package top.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.IdDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典明细表(AnanDictionaryDetail)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统通用字典明细表更新DTO", description = "表(anan_dictionary_detail)的对应的更新DTO")
public class AnanDictionaryDetailUpdateDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 609095667456068012L;

    @NotNull(message = "字典明细键" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", required = true)
    private Long name;

    @ApiModelProperty(value = "字典明细值表示字面意义")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "字典明细值不能包含特殊字符")
    private String value;

    @NotNull(message = "字典ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典ID，取值于字典明细表AnanDictionaryEntity.id", required = true)
    private Long dictionaryId;

    @NotNull(message = "顺序" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", required = true)
    private Integer sort;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "标准代码不能包含特殊字符")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "字典说明")
    private String description;
}
