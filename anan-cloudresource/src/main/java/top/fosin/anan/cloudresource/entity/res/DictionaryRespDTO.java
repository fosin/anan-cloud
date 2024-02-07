package top.fosin.anan.cloudresource.entity.res;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.res.IdCreateUpdateVO;

/**
 * 系统通用字典表(AnanDictionary)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Schema(description = "通用字典的响应DTO")
public class DictionaryRespDTO extends IdCreateUpdateVO<Long> {
    private static final long serialVersionUID = 611539841082760505L;
    @Schema(description = "字典名称", example = "String")
    private String name;

    @Schema(description = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", example = "Integer")
    private Byte type;

    @Schema(description = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    private String scope;

    @Schema(description = "详细说明", example = "String")
    private String description;

}
