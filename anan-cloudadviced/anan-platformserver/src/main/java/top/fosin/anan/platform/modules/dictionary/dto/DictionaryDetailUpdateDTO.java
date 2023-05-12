package top.fosin.anan.platform.modules.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 系统通用字典明细表(anan_dictionary_detail)更新DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统通用字典明细表更新DTO", description = "系统通用字典明细表(anan_dictionary_detail)更新DTO")
public class DictionaryDetailUpdateDTO extends Id<Long>
        implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = -33466996138421999L;

    @NotNull(message = "字典明细键" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码,创建和更新时必填")
    private Long code;

    @ApiModelProperty(value = "字典明细值表示字面意义")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "字典明细值不能包含特殊字符")
    private String name;

    @NotNull(message = "字典序号" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典ID，取值于字典明细表Dictionary.id,创建和更新时必填")
    private Long dictionaryId;

    @NotNull(message = "顺序" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前,创建和更新时必填")
    private Integer sort;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11,创建和更新时必填")
    private Integer status;

    @ApiModelProperty(value = "标准代码")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "标准代码不能包含特殊字符")
    private String scode;

    @ApiModelProperty(value = "作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @NotNull(message = "使用标志" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性,创建和更新时必填")
    private Integer used;

    @ApiModelProperty(value = "字典说明")
    private String description;

    @Override
    public Long getFkValue() {
        return dictionaryId;
    }

    @Override
    public void setFkValue(Long foreingKey) {
        this.dictionaryId = foreingKey;
    }

    @Override
    public String getFkName() {
        return "dictionaryId";
    }
}
