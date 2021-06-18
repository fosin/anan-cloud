package top.fosin.anan.platform.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.cloudresource.dto.res.AnanUserRespDto;

/**
 * 系统用户表(AnanUser)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户表响应DTO-带密码", description = "用户的响应DTO-带密码")
public class AnanUserRespPassDto extends AnanUserRespDto {
    private static final long serialVersionUID = -37913233914512798L;

    @ApiModelProperty(value = "用户密码", example = "String")
    private String password;

}
