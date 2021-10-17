package top.fosin.anan.cloudresource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.cloudresource.dto.req.AnanUserRoleDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;

import java.io.Serializable;
import java.util.List;

/**
 * 系统用户表(AnanUser)更新DTO
 *
 * @author fosin
 * @date 2019-01-27 12:23:44
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户表更新DTO", description = "用户的更新DTO")
public class AnanUserAuthDto extends AnanUserRespDto implements Serializable {
    private static final long serialVersionUID = -38545495043403316L;

    @ApiModelProperty(value = "用户拥有的角色")
    private List<AnanUserRoleDto> userRoles;

    @ApiModelProperty(value = "用户密码", example = "123456")
    private String password;

}
