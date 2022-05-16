package top.fosin.anan.platform.modules.pub.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 国际化语言集(AnanInternational)请求DTO
 *
 * @author fosin
 * @date 2020-12-08 20:54:17
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "国际化语言集请求DTO", description = "国际化语言的请求DTO")
public class InternationalReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 380520117161696142L;

    @NotBlank(message = "标识" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "标识", example = "String")
    private String code;

    @NotBlank(message = "名称" + "{javax.validation.constraints.NotBlank.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @NotNull(message = "状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "0")
    private Integer status;

    @NotNull(message = "默认标志" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "默认标志" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "默认标志", example = "0")
    private Integer defaultFlag;

}
