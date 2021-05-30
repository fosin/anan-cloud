package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

import java.io.Serializable;

/**
 * 系统用户角色表(AnanUserRole)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统用户角色表响应DTO", description = "系统用户角色的响应DTO")
public class AnanUserRoleRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = -30073122110919311L;
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户ID", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "角色ID", example = "Long")
    private Long roleId;

}
