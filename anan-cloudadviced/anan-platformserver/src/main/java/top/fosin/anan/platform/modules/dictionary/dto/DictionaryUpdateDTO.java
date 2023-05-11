package top.fosin.anan.platform.modules.dictionary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
        
/**
 * 系统通用字典表(anan_dictionary)更新DTO
 *
 * @author fosin
 * @date 2023-05-11 18:11:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "系统通用字典表更新DTO", description = "系统通用字典表(anan_dictionary)更新DTO")
public class DictionaryUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -53234797251889310L;
    
    @NotBlank(message = "字典名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "字典名称", required = true, example = "String")
    private String name;

    @NotNull(message = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", required = true, example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    private String scope;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

}
