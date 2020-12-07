package com.github.fosin.anan.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 国际化(AnanInternational)查询DTO
 *
 * @author fosin
 * @date 2020-12-04 11:09:50
 * @since 1.0.0
 */
@Data
@ApiModel(value = "国际化查询DTO", description = "表(anan_international)的对应的查询DTO")
public class AnanInternationalRetrieveDto implements Serializable {
    private static final long serialVersionUID = -94616377006269744L;

    @NotNull(message = "主键" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "主键", example = "Integer")
    private Integer id;

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
