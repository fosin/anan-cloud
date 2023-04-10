package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.res.IdCreateUpdateDto;

import java.util.Date;

/**
 * 用于存放各种分类分组的个性化参数(AnanParameter)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:14
 * @since 2.6.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于存放各种分类分组的个性化参数响应DTO", description = "通用参数的响应DTO")
public class ParameterRespDto extends IdCreateUpdateDto<Long> {
    private static final long serialVersionUID = -73214133063602891L;
    @ApiModelProperty(value = "参数键", example = "String")
    private String name;

    @ApiModelProperty(value = "参数值", example = "String")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10", example = "Integer")
    private Integer type;

    @ApiModelProperty(value = "参数作用域", example = "String")
    private String scope;

    @ApiModelProperty(value = "默认值", example = "String")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述", example = "String")
    private String description;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "生效时间", example = DateTimeUtil.DATETIME_PATTERN)
    private Date applyTime;

    @ApiModelProperty(value = "生效人", example = "Long")
    private Long applyBy;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态", example = "Integer")
    private Integer status;

}
