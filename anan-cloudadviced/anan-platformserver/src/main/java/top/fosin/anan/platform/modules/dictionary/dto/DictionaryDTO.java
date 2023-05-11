package top.fosin.anan.platform.modules.dictionary.dto;

                                                                                                                                                                                    

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
/**
 * 系统通用字典表(anan_dictionary)DTO
 *
 * @author fosin
 * @date 2023-05-11 18:11:48
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统通用字典表DTO", description = "系统通用字典表(anan_dictionary)DTO")
public class DictionaryDTO extends Id<Long> {
    private static final long serialVersionUID = -35716769565516924L;
    @ApiModelProperty(value = "字典名称", required = true, example = "String")
    private String name;

    @ApiModelProperty(value = "字典类别，区别字典的大分类，取值于表anan_dictionary.code = 1数据", required = true, example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "字典作用域，以字典类别为前提，在字典类别基础上再次细化分类字典", example = "String")
    private String scope;

    @ApiModelProperty(value = "详细说明", example = "String")
    private String description;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "修改人", required = true, example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期", required = true, example = "Date")
    private Date updateTime;

}
