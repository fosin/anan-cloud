package top.fosin.anan.platform.modules.international.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.valid.group.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 国际化语言集(anan_international)创建DTO
 *
 * @author fosin
 * @date 2023-05-12
 */
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@ApiModel(value = "国际化语言集创建DTO", description = "国际化语言集(anan_international)创建DTO")
public class InternationalCreateDTO {
    private static final long serialVersionUID = 111078957729112772L;

    @NotBlank(message = "标识" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "标识", example = "String")
    private String code;

    @NotBlank(message = "名称" + "{javax.validation.constraints.NotBlank.message}", groups = Create.class)
    @ApiModelProperty(value = "名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @NotNull(message = "状态" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "状态" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "0")
    private Byte status;

    @NotNull(message = "默认标志" + "{javax.validation.constraints.NotNull.message}", groups = Create.class)
    @PositiveOrZero(message = "默认标志" + "{javax.validation.constraints.Positive.message}", groups = Create.class)
    @ApiModelProperty(value = "默认标志", example = "0")
    private Integer defaultFlag;

}
