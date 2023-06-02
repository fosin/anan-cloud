package top.fosin.anan.platform.modules.dictionary.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

        /**
 * 系统通用字典明细表(anan_dictionary_detail)单体VO
 *
 * @author fosin
 * @date 2023-05-21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统通用字典明细表单体VO", description = "系统通用字典明细表(anan_dictionary_detail)单体VO")
public class DictionaryDetailVO extends Id<Long> {
    private static final long serialVersionUID = -18193571078330100L;
    @ApiModelProperty(value = "取值于字典表anan_dictionary.id")
    private Long dictionaryId;

    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码")
    private Long code;

    @ApiModelProperty(value = "字典明细字面说明")
    private String name;

    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前")
    private Short sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Byte status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域")
    private String scope;

    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性")
    private Byte used;

    @ApiModelProperty(value = "详细说明")
    private String description;

}
