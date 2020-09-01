package com.github.fosin.anan.cloudresource.dto.request;

import com.github.fosin.anan.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典表(AnanDictionary)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典表更新DTO", description = "表(anan_dictionary)的对应的更新DTO")
public class AnanDictionaryUpdateDto implements Serializable {
    private static final long serialVersionUID = -36896996810986681L;

    @NotNull(message = "字典代码" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典代码, 主键", required = true)
    private Long id;

    @NotBlank(message = "字典名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "字典名称", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotNull(message = "字典类别" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", required = true)
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

}
