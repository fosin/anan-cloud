package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化语言字符集(AnanInternationalCharset)更新DTO
 *
 * @author fosin
 * @date 2020-12-09 10:34:34
 * @since 1.0.0
 */
@Data
@ApiModel(value = "国际化语言字符集更新DTO", description = "表(anan_international_charset)的对应的更新DTO")
public class AnanInternationalCharsetUpdateDto implements Serializable {
    private static final long serialVersionUID = -22137097820187644L;

    @NotNull(message = "主键" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "主键", example = "Long")
    private Long id;

    @NotNull(message = "国际化语言ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "国际化语言ID", example = "Integer")
    private Integer internationalId;

    @NotNull(message = "服务ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "服务ID", example = "Integer")
    private Integer serviceId;

    @ApiModelProperty(value = "自定义字符集", example = "String")
    private String charset;

    @NotNull(message = "状态：0=启用，1=禁用" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "状态：0=启用，1=禁用", example = "Integer")
    private Integer status;

}
