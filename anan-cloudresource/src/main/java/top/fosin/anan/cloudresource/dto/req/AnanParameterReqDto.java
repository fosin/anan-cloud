package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用于存放各种分类分组的个性化参数(AnanParameter)请求DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:17
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "用于存放各种分类分组的个性化参数请求DTO", description = "通用参数的请求DTO")
public class AnanParameterReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = -95372770044687456L;

    @ApiModelProperty(value = "参数键")
    @NotBlank(message = "参数键{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @Pattern(regexp = "[\\w]{1,64}", message = "参数键只能大小写字母、数字、下杠(_)组合而成,长度不超过64位",
            groups = {Create.class, Update.class})
    private String name;

    @ApiModelProperty(value = "参数值")
    private String value;

    @ApiModelProperty(value = "参数分类：具体取值于字典表anan_dictionary.code=10")
    @NotNull(message = "参数分类{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "参数分类{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    private Integer type;

    @ApiModelProperty(value = "参数作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @ApiModelProperty(value = "参数描述")
    private String description;

    @ApiModelProperty(value = "参数状态：0=正常状态、1=修改状态、2=删除状态")
    @NotNull(message = "参数状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 0, message = "参数状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    private Integer status;

    @ApiModelProperty(value = "应用时间")
    private Date applyTime;

}
