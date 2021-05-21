package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.OrganizIdCreateUpdateDto;

import java.io.Serializable;

/**
 * 系统角色表(AnanRole)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统角色表响应DTO", description = "系统角色的响应DTO")
public class AnanRoleRespDto extends OrganizIdCreateUpdateDto<Long> implements Serializable {
    private static final long serialVersionUID = -40320128611775614L;

    @ApiModelProperty(value = "角色名称", example = "String")
    private String name;

    @ApiModelProperty(value = "角色标识", example = "String")
    private String value;

    @ApiModelProperty(value = "角色说明", example = "String")
    private String tips;

    @ApiModelProperty(value = "使用状态：0=启用，1=禁用，具体取值于字典表anan_dictionary.code=11", example = "Integer")
    private Integer status;

    @ApiModelProperty(value = "内置标志：是否是系统内置角色，内置角色不能被用户删除和修改，0=不是 1=是", example = "Integer")
    private Integer builtIn;

}
