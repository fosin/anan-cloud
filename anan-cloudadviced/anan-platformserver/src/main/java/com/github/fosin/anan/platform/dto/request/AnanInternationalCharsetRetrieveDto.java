package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化明显(AnanInternationalCharset)查询DTO
 *
 * @author fosin
 * @date 2020-12-04 11:05:54
 * @since 1.0.0
 */
@Data
@ApiModel(value = "国际化明显查询DTO", description = "表(anan_international_charset)的对应的查询DTO")
public class AnanInternationalCharsetRetrieveDto implements Serializable {
    private static final long serialVersionUID = 281798390262854865L;

    @NotNull(message = "${column.comment}" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "${column.comment}", example = "Long")
    private Long id;

    @NotNull(message = "国际化语言ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "国际化语言ID", example = "Integer")
    private Integer internationalId;

    @NotBlank(message = "代码名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "代码名称", example = "String")
    private String key;

    @NotBlank(message = "对应语言显示名" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "对应语言显示名", example = "String")
    private String value;

    @NotNull(message = "模块ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "模块ID", example = "Integer")
    private Integer moduleId;

    @NotNull(message = "状态：0=启用，1=禁用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

}
