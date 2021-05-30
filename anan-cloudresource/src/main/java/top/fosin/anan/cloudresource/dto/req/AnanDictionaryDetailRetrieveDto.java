package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.core.util.RegexUtil;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统通用字典明细表(AnanDictionaryDetail)查询DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "系统通用字典明细表查询DTO", description = "系统通用字典明细的查询DTO")
public class AnanDictionaryDetailRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> implements Serializable {
    private static final long serialVersionUID = 507206776709737910L;

    @ApiModelProperty(value = "字典明细键，不能重复，字典内明细项唯一代码")
    private Long name;

    @ApiModelProperty(value = "字典明细值表示字面意义")
    private String value;

    @ApiModelProperty(value = "取值于字典明细表AnanDictionaryDetailEntity.code")
    private Long dictionaryId;

    @ApiModelProperty(value = "顺序，用于显示数据时的顺序，数值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "标准代码，该字段通常用于对接标准字典")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scode;

    @ApiModelProperty(value = "作用域，用于字典明细项的作用域")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "作用域不能包含特殊字符")
    private String scope;

    @ApiModelProperty(value = "使用标志：0=未使用，1=已使用，已使用的字典就不能再修改name属性")
    private Integer used;

    @ApiModelProperty(value = "字典说明")
    private String description;

    @ApiModelProperty(value = "创建人", example = "1")
    private Long createBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", example = "2021-05-08 13:25:11")
    private Date createTime;

    @ApiModelProperty(value = "修改人", example = "1")
    private Long updateBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "修改日期", example = "2021-05-08 13:25:11")
    private Date updateTime;

    @ApiModelProperty(value = "删除人", example = "1")
    private Long deleteBy;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "删除日期", example = "2021-05-08 13:25:11")
    private Date deleteime;

    @ApiModelProperty(value = "删除标志")
    private Integer deleted;
}
