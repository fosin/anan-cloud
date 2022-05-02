package top.fosin.anan.cloudresource.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

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
@ApiModel(value = "用户角色表响应DTO", description = "用户角色的响应DTO")
public class AnanUserRoleRespDto extends IdDto<Long> {
    private static final long serialVersionUID = -30073122110919311L;
    @ApiModelProperty(value = "机构序号", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户序号", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "角色序号", example = "Long")
    private Long roleId;

}
