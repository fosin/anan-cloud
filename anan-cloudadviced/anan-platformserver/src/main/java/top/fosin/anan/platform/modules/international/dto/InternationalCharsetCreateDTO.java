package top.fosin.anan.platform.modules.international.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * 国际化语言字符集(anan_international_charset)创建DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "国际化语言字符集(anan_international_charset)创建DTO")
public class InternationalCharsetCreateDTO {
    private static final long serialVersionUID = 352619438652226100L;

    @NotNull(message = "国际化语言序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "国际化语言序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "国际化语言序号", example = "0")
    private Long internationalId;

    @NotNull(message = "服务序号" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @Positive(message = "服务序号" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "服务序号", example = "0")
    private Long serviceId;

    @Schema(description = "自定义字符集", example = "String")
    private String charset;

    @NotNull(message = "使用状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "使用状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "使用状态：0=启用，1=禁用", example = "0")
    private Byte status;

}
