package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.res.IdCreateUpdateDto;

/**
 * 系统通用字典明细表(AnanDictionaryDetail)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "通用字典明细表响应DTO", description = "通用字典明细的响应DTO")
public class DictionaryDetailRespDto extends IdCreateUpdateDto<Long> {
    private static final long serialVersionUID = -17948374006352783L;
    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", example = "Long")
    private Long name;

    @ApiModelProperty(value = "字典明细值表示字面意义", example = "String")
    private String value;

    @ApiModelProperty(value = "取值于字典明细表DictionaryDetail.code", example = "Long")
    private Long dictionaryId;

    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", example = "Integer")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典", example = "String")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域", example = "String")
    private String scope;

    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", example = "Integer")
    private Integer used;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

}
