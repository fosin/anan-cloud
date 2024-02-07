package top.fosin.anan.platform.modules.dictionary.dto;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 系统通用字典表(anan_dictionary)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统通用字典表(anan_dictionary)DTO")
public class DictionaryDTO extends Id<Long> {
    private static final long serialVersionUID = -35716769565516924L;
    @Schema(description = "字典名称", example = "String")
    private String name;

    @Schema(description = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", example = "Integer")
    private Byte type;

    @Schema(description = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    private String scope;

    @Schema(description = "详细说明", example = "String")
    private String description;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "修改人", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "修改日期", example = "Date")
    private Date updateTime;

}
