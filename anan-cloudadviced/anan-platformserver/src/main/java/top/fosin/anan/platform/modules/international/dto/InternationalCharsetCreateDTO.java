package top.fosin.anan.platform.modules.international.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.req.LogiSortQuery;
import top.fosin.anan.data.module.LogiQueryRule;
import top.fosin.anan.data.module.SortRule;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * 国际化语言字符集(anan_international_charset)创建DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "国际化语言字符集创建DTO", description = "国际化语言字符集(anan_international_charset)创建DTO")
public class InternationalCharsetCreateDTO extends LogiSortQuery<LogiQueryRule, SortRule, Long> {
    private static final long serialVersionUID = 352619438652226100L;

    @NotNull(message = "国际化语言序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "国际化语言序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "国际化语言序号", example = "0")
    private Long internationalId;

    @NotNull(message = "服务序号" + "{javax.validation.constraints.NotNull.message}")
    @Positive(message = "服务序号" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "服务序号", example = "0")
    private Long serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @PositiveOrZero(message = "使用状态" + "{javax.validation.constraints.Positive.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用", example = "0")
    private Integer status;

}