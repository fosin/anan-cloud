package top.fosin.anan.platform.modules.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
        
/**
 * 系统通用字典明细表(anan_dictionary_detail)更新DTO
 *
 * @author fosin
 * @date 2023-05-11 18:11:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统通用字典明细表更新DTO", description = "系统通用字典明细表(anan_dictionary_detail)更新DTO")
public class DictionaryDetailUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -33466996138421999L;
    
    @NotNull(message = "取值于字典表anan_dictionary.id" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "取值于字典表anan_dictionary.id", required = true, example = "Long")
    private Long dictionaryId;

    @NotNull(message = "字典明细键，不能重复，字典内明细项唯一代码" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", required = true, example = "Long")
    private Long code;

    @ApiModelProperty(value = "字典明细字面说明", example = "String")
    private String name;

    @NotEmpty(message = "顺序，用于显示数据时的顺序，数值越小越靠前" + "{javax.validation.constraints.NotEmpty.message}")
    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", required = true, example = "Object")
    private Object sort;

    @NotNull(message = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true, example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典", example = "String")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域", example = "String")
    private String scope;

    @NotNull(message = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", required = true, example = "Integer")
    private Integer used;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

}
