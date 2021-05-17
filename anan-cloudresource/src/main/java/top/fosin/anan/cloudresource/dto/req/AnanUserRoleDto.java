package top.fosin.anan.cloudresource.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.OrganizIdDto;

import java.io.Serializable;

/**
 * 系统角色表(AnanRole)查询DTO
 *
 * @author fosin
 * @date 2019-01-27 18:27:18
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统角色表查询DTO", description = "表(anan_role)的对应的查询DTO")
public class AnanUserRoleDto extends OrganizIdDto<Long> implements Serializable {
    private static final long serialVersionUID = 431913654589649616L;

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
