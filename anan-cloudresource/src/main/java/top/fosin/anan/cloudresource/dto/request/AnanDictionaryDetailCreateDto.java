package top.fosin.anan.cloudresource.dto.request;

import top.fosin.anan.core.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典明细表(AnanDictionaryDetail)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典明细表创建DTO", description = "表(anan_dictionary_detail)的对应的创建DTO")
public class AnanDictionaryDetailCreateDto implements Serializable {
    private static final long serialVersionUID = -81112310562332311L;

    @NotNull(message = "字典明细键" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", required = true)
    private Long name;

    @ApiModelProperty(value = "字典明细值表示字面意义")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "字典明细值不能包含特殊字符")
    private String value;

    @NotNull(message = "字典ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典ID，取值于字典明细表AnanDictionaryDetailEntity.code", required = true)
    private Long dictionaryId;

    @NotNull(message = "顺序" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", required = true)
    private Integer sort;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true)
    private Integer status;

    @ApiModelProperty(value = "标准代码")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "标准代码不能包含特殊字符")
    private String scode;

    @ApiModelProperty(value = "作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @NotNull(message = "使用标志" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", required = true)
    private Integer used;

    @ApiModelProperty(value = "字典说明")
    private String description;
}
