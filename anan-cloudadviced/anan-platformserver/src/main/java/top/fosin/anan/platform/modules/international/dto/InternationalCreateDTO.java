package top.fosin.anan.platform.modules.international.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * 国际化语言集(anan_international)创建DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Schema(description = "国际化语言集(anan_international)创建DTO")
public class InternationalCreateDTO {
    private static final long serialVersionUID = 111078957729112772L;

    @NotBlank(message = "标识" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "标识", example = "String")
    private String code;

    @NotBlank(message = "名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Create.class)
    @Schema(description = "名称", example = "String")
    private String name;

    @Schema(description = "图标", example = "String")
    private String icon;

    @NotNull(message = "状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "状态" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "状态：0=启用，1=禁用", example = "0")
    private Byte status;

    @NotNull(message = "默认标志" + "{jakarta.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "默认标志" + "{jakarta.validation.constraints.Positive.message}", groups = Create.class)
    @Schema(description = "默认标志", example = "0")
    private Integer defaultFlag;

}
