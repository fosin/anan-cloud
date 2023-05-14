package top.fosin.anan.cloudresource.entity.res;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 用于存放各种分类分组的个性化参数(anan_parameter)DTO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于存放各种分类分组的个性化参数DTO", description = "用于存放各种分类分组的个性化参数(anan_parameter)DTO")
public class ParameterDTO extends Id<Long> {
    private static final long serialVersionUID = -56643720935660246L;
    @ApiModelProperty(value = "参数键", required = true, example = "String")
    private String name;

    @ApiModelProperty(value = "参数值", example = "String")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10", required = true, example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "参数作用域", example = "String")
    private String scope;

    @ApiModelProperty(value = "默认值", example = "String")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述", example = "String")
    private String description;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期", required = true, example = "Date")
    private Date updateTime;

    @ApiModelProperty(value = "修改人", required = true, example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "发布时间", example = "Date")
    private Date applyTime;

    @ApiModelProperty(value = "创建人", example = "Long")
    private Long applyBy;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", required = true, example = "Integer")
    private Integer status;

}
