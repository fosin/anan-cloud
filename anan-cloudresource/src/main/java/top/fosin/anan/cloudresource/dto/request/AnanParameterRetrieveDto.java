package top.fosin.anan.cloudresource.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.model.module.QuerySortRuleDto;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.module.QueryRule;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用于存放各种分类分组的个性化参数(AnanParameter)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:17
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用于存放各种分类分组的个性化参数查询DTO", description = "表(anan_parameter)的对应的查询DTO")
public class AnanParameterRetrieveDto extends QuerySortRuleDto<QueryRule, SortRule> implements Serializable {
    private static final long serialVersionUID = -95372770044687456L;

    @ApiModelProperty(value = "参数ID, 主键")
    private Long id;

    @ApiModelProperty(value = "参数键")
    @NotBlank(message = "参数键{javax.validation.constraints.NotBlank.message}")
    private String name;

    @ApiModelProperty(value = "参数值")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10")
    @NotNull(message = "参数分类{javax.validation.constraints.NotNull.message}")
    private Integer type;

    @ApiModelProperty(value = "参数作用域")
    private String scope;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述")
    private String description;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    private Integer status;

    @ApiModelProperty(value = "应用日期")
    private Date applyTime;

    @ApiModelProperty(value = "创建人", example = "Long")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "修改人", example = "Long")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期", example = "Date")
    private Date updateTime;

}
