package top.fosin.anan.platform.modules.international.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;
import top.fosin.anan.data.valid.group.Update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * 国际化语言集(anan_international)更新DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "国际化语言集(anan_international)更新DTO")
public class InternationalUpdateDTO extends Id<Long> {
    private static final long serialVersionUID = -44625865080513112L;

    @NotBlank(message = "标识" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "标识", example = "String")
    private String code;

    @NotBlank(message = "名称" + "{jakarta.validation.constraints.NotBlank.message}", groups = Update.class)
    @Schema(description = "名称", example = "String")
    private String name;

    @Schema(description = "图标", example = "String")
    private String icon;

    @NotNull(message = "状态" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "状态" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "状态：0=启用，1=禁用", example = "0")
    private Byte status;

    @NotNull(message = "默认标志" + "{jakarta.validation.constraints.NotNull.message}", groups = Update.class)
    @PositiveOrZero(message = "默认标志" + "{jakarta.validation.constraints.Positive.message}", groups = Update.class)
    @Schema(description = "默认标志", example = "0")
    private Integer defaultFlag;

}
