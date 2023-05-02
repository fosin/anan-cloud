package top.fosin.anan.cloudresource.entity.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统用户表(AnanUser)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户表响应DTO", description = "用户的响应DTO")
public class UserRespDTO extends UserDTO {
    private static final long serialVersionUID = -37913233914512798L;

    @ApiModelProperty(value = "顶级机构序号", example = "1")
    private Long topId;
}
