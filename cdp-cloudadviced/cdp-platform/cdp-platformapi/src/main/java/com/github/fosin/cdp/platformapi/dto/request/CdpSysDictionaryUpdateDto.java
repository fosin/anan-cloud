package com.github.fosin.cdp.platformapi.dto.request;

import com.github.fosin.cdp.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.github.fosin.cdp.util.DateTimeUtil;

/**
 * 系统通用字典表(CdpSysDictionary)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 18:34:10
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典表更新DTO", description = "表(cdp_sys_dictionary)的对应的更新DTO")
public class CdpSysDictionaryUpdateDto implements Serializable {
    private static final long serialVersionUID = -38014192579289990L;

    @NotNull
    @ApiModelProperty(value = "字典代码", example = "Long", required = true)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "字典名称", example = "String", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotNull
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表cdp_sys_dictionary.id = 1数据", example = "Integer", required = true)
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

}