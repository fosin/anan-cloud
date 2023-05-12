package top.fosin.anan.platform.modules.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * 系统通用字典表(anan_dictionary)创建DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统通用字典表创建DTO", description = "系统通用字典表(anan_dictionary)创建DTO")
public class DictionaryCreateDTO extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -96200070903677903L;
    
    @NotBlank(message = "字典名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "字典名称", required = true, example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotNull(message = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "字典类别" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", required = true, example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

}
