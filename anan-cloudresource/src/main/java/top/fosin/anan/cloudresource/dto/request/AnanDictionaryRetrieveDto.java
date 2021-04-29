package top.fosin.anan.cloudresource.dto.request;

import top.fosin.anan.core.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统通用字典表(AnanDictionary)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:34:10
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统通用字典表查询DTO", description = "表(anan_dictionary)的对应的查询DTO")
public class AnanDictionaryRetrieveDto implements Serializable {
    private static final long serialVersionUID = 480249603699448721L;

    @ApiModelProperty(value = "字典代码")
    private Long id;

    @ApiModelProperty(value = "字典名称")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典")
    @Pattern(regexp =RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "字典说明")
    private String description;

}
