package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

import java.io.Serializable;

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
@ApiModel(value = "系统通用字典表响应DTO", description = "系统通用字典的响应DTO")
public class AnanDictionaryRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = 611539841082760505L;
    @ApiModelProperty(value = "字典名称", example = "String")
    private String name;

    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    private String scope;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

}
