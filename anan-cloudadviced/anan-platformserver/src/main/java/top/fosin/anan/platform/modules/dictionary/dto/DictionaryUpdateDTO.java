package top.fosin.anan.platform.modules.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
/**
 * 系统通用字典表(anan_dictionary)更新DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统通用字典表更新DTO", description = "系统通用字典表(anan_dictionary)更新DTO")
public class DictionaryUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -53234797251889310L;
    
    @NotBlank(message = "字典名称" + "{javax.validation.constraints.NotBlank.message}", groups = Update.class)
    @ApiModelProperty(value = "字典名称", required = true, example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符", groups = Update.class)
    private String name;

    @NotNull(message = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据" + "{javax.validation.constraints.NotNull.message}", groups = Update.class)
    @Positive(message = "字典类别" + "{javax.validation.constraints.Positive.message}", groups = Update.class)
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", required = true, example = "Integer")
    private Byte type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符", groups = Update.class)
    private String scope;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

}
