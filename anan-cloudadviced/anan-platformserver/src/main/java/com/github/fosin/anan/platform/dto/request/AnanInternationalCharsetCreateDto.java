package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化语言字符集(AnanInternationalCharset)创建DTO
 *
 * @author fosin
 * @date 2020-12-05 22:10:56
 * @since 1.0.0
 */
@Data
@ApiModel(value = "国际化语言字符集创建DTO", description = "表(anan_international_charset)的对应的创建DTO")
public class AnanInternationalCharsetCreateDto implements Serializable {
    private static final long serialVersionUID = -29738072608823628L;

    @NotNull(message = "国际化语言ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "国际化语言ID", example = "Integer")
    private Integer internationalId;

    @NotBlank(message = "代码名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "代码名称", example = "String")
    private String key;

    @NotBlank(message = "对应语言显示名" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "对应语言显示名", example = "String")
    private String value;

    @NotNull(message = "服务ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "服务ID", example = "Integer")
    private Integer serviceId;

    @NotNull(message = "状态：0=启用，1=禁用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

}
