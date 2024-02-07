package top.fosin.anan.platform.modules.dictionary.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
/**
 * 系统通用字典表(anan_dictionary)更新DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "系统通用字典表(anan_dictionary)更新DTO")
public class DictionaryUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -53234797251889310L;
    
    @NotBlank(message = "字典名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "字典名称", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Update.class)
    private String name;

    @NotNull(message = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "字典类别" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", example = "Integer")
    private Byte type;

    @Schema(description = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符", groups = Update.class)
    private String scope;

    @Schema(description = "详细说明", example = "String")
    private String description;

}
