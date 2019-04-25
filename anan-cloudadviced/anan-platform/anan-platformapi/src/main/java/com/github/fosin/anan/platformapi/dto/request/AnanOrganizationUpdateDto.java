package com.github.fosin.anan.platformapi.dto.request;

import com.github.fosin.anan.util.RegexUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 系统机构表(AnanOrganization)更新DTO
 *
 * @author fosin
 * @date 2019-02-19 18:17:04
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统机构表更新DTO", description = "表(anan_organization)的对应的更新DTO")
public class AnanOrganizationUpdateDto implements Serializable {
    private static final long serialVersionUID = -70574823368846228L;

    @NotNull(message = "机构ID" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "机构ID, 主键", example = "Long", required = true)
    private Long id;

    @NotNull(message = "父机构编码" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "父机构编码，取值于id，表示当前数据所属的父类机构", example = "Long", required = true)
    private Long pId;

    @NotNull(message = "顶级机构编码：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "顶级机构编码：一般指用户注册的机构，通常是一个集团组的最高级别机构，取值于id", example = "Long", required = true)
    private Long topId;

    @NotBlank(message = "机构编码" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "机构编码，自定义机构编码，下级机构必须以上级机构编码为前缀", example = "String", required = true)
    @Pattern(regexp = "[\\w]{1,64}", message = "机构编码只能大小写字母、数字、下杠(_)组合而成,长度不超过64位")
    private String code;

    @NotBlank(message = "机构名称" + "{javax.validation.constraints.NotBlank.message}")
    @ApiModelProperty(value = "机构名称", example = "String", required = true)
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String name;

    @NotNull(message = "深度" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "深度", example = "Integer", required = true)
    private Integer level;

    @ApiModelProperty(value = "机构全名", example = "String")
    @Pattern(regexp = RegexUtil.SPECIAL, message = "名称不能包含特殊字符")
    private String fullname;

    @ApiModelProperty(value = "机构地址", example = "String")
    private String address;

    @ApiModelProperty(value = "机构电话", example = "String")
    private String telphone;

    @NotNull(message = "使用状态" + "{javax.validation.constraints.NotNull.message}")
    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer", required = true)
    private Integer status;

}
