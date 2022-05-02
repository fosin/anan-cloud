package top.fosin.anan.platform.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.model.dto.QuerySortRuleDto;
import top.fosin.anan.model.module.LogicalQueryRule;
import top.fosin.anan.model.module.SortRule;
import top.fosin.anan.model.valid.group.Create;
import top.fosin.anan.model.valid.group.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 国际化语言字符集请求DTO
 *
 * @author fosin
 * @date 2020-12-09 10:34:35
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "国际化语言字符集请求DTO", description = "国际化语言字符集请求DTO")
public class AnanInternationalCharsetReqDto extends QuerySortRuleDto<LogicalQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 329752756986005664L;

    @NotNull(message = "国际化语言序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "国际化语言序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "国际化语言序号", example = "0")
    private Long internationalId;

    @NotNull(message = "服务序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 1, message = "服务序号" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "服务序号", example = "0")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Min(value = 0, message = "使用状态" + "{javax.validation.constraints.Min.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用", example = "0")
    private Integer status;

}
