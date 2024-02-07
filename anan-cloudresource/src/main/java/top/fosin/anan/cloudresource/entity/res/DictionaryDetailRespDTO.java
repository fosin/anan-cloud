package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.res.IdCreateUpdateVO;

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
@Schema(description = "通用字典明细的响应DTO")
public class DictionaryDetailRespDTO extends IdCreateUpdateVO<Long> {
    private static final long serialVersionUID = -17948374006352783L;
    @Schema(description = "字典明细键，不能重复，字典内明细项唯一代码", example = "1")
    private Long code;

    @Schema(description = "字典明细值表示字面意义", example = "String")
    private String name;

    @Schema(description = "取值于字典明细表Dictionary.id", example = "1")
    private Long dictionaryId;

    @Schema(description = "顺序，用于显示数据时的顺序，数值越小越靠前", example = "1")
    private Short sort;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "0")
    private Byte status;

    @Schema(description = "标准代码，该字段通常用于对接标准字典", example = "String")
    private String scode;

    @Schema(description = "作用域，用于字典明细项的作用域", example = "String")
    private String scope;

    @Schema(description = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", example = "0")
    private Byte used;

    @Schema(description = "详细说明", example = "String")
    private String description;

}
