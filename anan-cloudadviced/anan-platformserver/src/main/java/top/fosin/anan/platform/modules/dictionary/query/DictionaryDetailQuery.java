package top.fosin.anan.platform.modules.dictionary.query;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

        /**
 * 系统通用字典明细表(anan_dictionary_detail)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统通用字典明细表(anan_dictionary_detail)通用查询DTO")
public class DictionaryDetailQuery extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 490059349672474320L;
    
    @Schema(description = "取值于字典表anan_dictionary.id", example = "Long")
    private Long dictionaryId;

    @Schema(description = "字典明细键，不能重复，字典内明细项唯一代码", example = "Long")
    private Long code;

    @Schema(description = "字典明细字面说明", example = "String")
    private String name;

    @Schema(description = "顺序，用于显示数据时的顺序，数值越小越靠前", example = "Short")
    private Short sort;

    @Schema(description = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Byte")
    private Byte status;

    @Schema(description = "标准代码，该字段通常用于对接标准字典", example = "String")
    private String scode;

    @Schema(description = "作用域，用于字典明细项的作用域", example = "String")
    private String scope;

    @Schema(description = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", example = "Byte")
    private Byte used;

    @Schema(description = "详细说明", example = "String")
    private String description;

}
