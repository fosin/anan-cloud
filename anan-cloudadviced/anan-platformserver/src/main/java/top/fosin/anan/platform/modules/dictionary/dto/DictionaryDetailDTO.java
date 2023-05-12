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
 * 系统通用字典明细表(anan_dictionary_detail)DTO
 *
 * @author fosin
 * @date 2023-05-11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统通用字典明细表DTO", description = "系统通用字典明细表(anan_dictionary_detail)DTO")
public class DictionaryDetailDTO extends Id<Long> {
    private static final long serialVersionUID = -95606844405674337L;
    @ApiModelProperty(value = "取值于字典表anan_dictionary.id", required = true, example = "Long")
    private Long dictionaryId;

    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码", required = true, example = "Long")
    private Long code;

    @ApiModelProperty(value = "字典明细字面说明", example = "String")
    private String name;

    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前", required = true, example = "Object")
    private Object sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", required = true, example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典", example = "String")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域", example = "String")
    private String scope;

    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性", required = true, example = "Integer")
    private Integer used;

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
