package com.github.fosin.anan.cloudresource.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统角色表(AnanRole)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:18
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统角色表查询DTO", description = "表(anan_role)的对应的查询DTO")
public class AnanRoleRetrieveDto implements Serializable {
    private static final long serialVersionUID = 431913654589649616L;

    @ApiModelProperty(value = "角色ID, 主键")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    private Long organizId;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色标识")
    private String value;

    @ApiModelProperty(value = "角色说明")
    private String tips;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11")
    private Integer status;

    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是")
    private Integer builtIn;

}
