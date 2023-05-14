package top.fosin.anan.platform.modules.parameter.vo;


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
 * 用于存放各种分类分组的个性化参数(anan_parameter)集合VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于存放各种分类分组的个性化参数集合VO", description = "用于存放各种分类分组的个性化参数(anan_parameter)集合VO")
public class ParameterListVO extends Id<Long> {
    private static final long serialVersionUID = 826753541333641687L;
    @ApiModelProperty(value = "参数键")
    private String name;

    @ApiModelProperty(value = "参数值")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10")
    private Integer type;

    @ApiModelProperty(value = "参数作用域")
    private String scope;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述")
    private String description;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "发布时间")
    private Date applyTime;

    @ApiModelProperty(value = "发布人")
    private Long applyBy;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    private Integer status;

}
