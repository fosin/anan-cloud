package top.fosin.anan.platform.modules.dictionary.query;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

        /**
 * 系统通用字典表(anan_dictionary)通用查询DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统通用字典表(anan_dictionary)通用查询DTO")
public class DictionaryQuery extends LogiSortQuery<LogiQueryRule,SortRule,Long> {
    private static final long serialVersionUID = -14796894220059275L;
    
    @Schema(description = "字典名称", example = "String")
    private String name;

    @Schema(description = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", example = "Integer")
    private Byte type;

    @Schema(description = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    private String scope;

    @Schema(description = "详细说明", example = "String")
    private String description;

}
