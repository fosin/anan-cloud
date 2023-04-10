package top.fosin.anan.platform.modules.pub.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.fosin.anan.data.entity.req.IdLogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;
import top.fosin.anan.data.valid.group.Create;
import top.fosin.anan.data.valid.group.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
public class InternationalCharsetReqDto extends IdLogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 329752756986005664L;

    @NotNull(message = "国际化语言序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "国际化语言序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "国际化语言序号", example = "0")
    private Long internationalId;

    @NotNull(message = "服务序号" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @Positive(message = "服务序号" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "服务序号", example = "0")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}",
            groups = {Create.class, Update.class})
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}",
            groups = {Create.class, Update.class})
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用", example = "0")
    private Integer status;

}
