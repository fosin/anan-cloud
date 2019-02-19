package com.github.fosin.cdp.platformapi.dto.request;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典表(CdpDictionary)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典表创建DTO", description = "表(cdp_dictionary)的对应的创建DTO")
public class CdpDictionaryCreateDto implements Serializable {
    private static final long serialVersionUID = -99315500947021174L;

    @NotBlank(message = "字典名称" + "{org.hibernate.validator.constraints.NotBlank.message}")
    @ApiModelProperty(value = "字典名称", example = "String", required = true)
    private String name;

    @NotNull(message = "字典类别，区别字典的大分类，取值于表cdp_dictionary.code = 1数据" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表cdp_dictionary.code = 1数据", example = "Integer", required = true)
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

}
