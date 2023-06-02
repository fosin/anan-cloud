package top.fosin.anan.platform.modules.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.prop.ForeignKeyProp;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 * 系统通用字典明细表(anan_dictionary_detail)更新DTO
 *
 * @author fosin
 * @date 2023-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统通用字典明细表更新DTO", description = "系统通用字典明细表(anan_dictionary_detail)更新DTO")
public class DictionaryDetailUpdateDTO extends Id<Long> implements ForeignKeyProp<Long> {
    private static final long serialVersionUID = 781081905426433692L;

    @NotNull(message = "字典序号" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "字典序号，取值于字典表anan_dictionary.id", required = true, example = "Long")
    private Long dictionaryId;

    @NotNull(message = "字典明细键" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "字典明细键", required = true, example = "Long")
    private Long code;

    @ApiModelProperty(value = "字典明细字面说明", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "字典明细值不能包含特殊字符", groups = Update.class)
    private String name;

    @NotNull(message = "顺序" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", required = true, example = "Short")
    private Short sort;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true, example = "Byte")
    private Byte status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "标准代码不能包含特殊字符", groups = Update.class)
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符", groups = Update.class)
    private String scope;

    @NotNull(message = "使用标志" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", required = true, example = "Byte")
    private Byte used;

    @ApiModelProperty(value = "详细说明", example = "String")
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
