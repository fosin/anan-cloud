package top.fosin.anan.cloudresource.dto.request;

import top.fosin.anan.core.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典表(AnanDictionary)创建DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典表创建DTO", description = "表(anan_dictionary)的对应的创建DTO")
public class AnanDictionaryCreateDto implements Serializable {
    private static final long serialVersionUID = -99315500947021174L;

    @NotBlank(message = "字典名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "字典名称", required = true)
    private String name;

    @NotNull(message = "字典类别" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", required = true)
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "字典说明")
    private String description;
}
