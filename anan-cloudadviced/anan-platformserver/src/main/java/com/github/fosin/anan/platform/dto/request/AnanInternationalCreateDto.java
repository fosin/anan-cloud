package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化语言集(AnanInternational)创建DTO
 *
 * @author fosin
 * @date 2020-12-05 22:10:56
 * @since 1.0.0
 */
@Data
@ApiModel(value = "国际化语言集创建DTO", description = "表(anan_international)的对应的创建DTO")
public class AnanInternationalCreateDto implements Serializable {
    private static final long serialVersionUID = 241629439266650229L;

    @NotBlank(message = "国际化通用标识" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "国际化通用标识", example = "String")
    private String code;

    @NotBlank(message = "国际化名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "国际化名称", example = "String")
    private String name;

    @ApiModelProperty(value = "图标", example = "String")
    private String icon;

    @NotNull(message = "状态：0=启用，1=禁用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

    @NotNull(message = "默认标志" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "默认标志", example = "Integer")
    private Integer defaultFlag;

}
