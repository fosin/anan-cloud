package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;

/**
 * 国际化语言集(AnanInternational)查询DTO
 *
 * @author fosin
 * @date 2020-12-08 20:54:17
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "国际化语言集查询DTO", description = "国际化语言的查询DTO")
public class AnanInternationalRetrieveDto extends QuerySortRuleDto<LogicalQueryRule, SortRule> {
    private static final long serialVersionUID = 380520117161696142L;

    @ApiModelProperty(value = "标识", example = "String")
    private String code;

    @ApiModelProperty(value = "名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "0")
    private Integer status;

    @ApiModelProperty(value = "默认标志", example = "0")
    private Integer defaultFlag;

}
