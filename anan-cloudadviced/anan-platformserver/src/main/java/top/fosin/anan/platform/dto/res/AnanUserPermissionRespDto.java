package top.fosin.anan.platform.dto.res;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.model.dto.IdDto;

/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(AnanUserPermission)响应DTO
 *
 * @author fosin
 * @date 2021-05-16 14:32:15
 * @since 2.6.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限响应DTO", description = "表(anan_user_permission)的响应DTO")
public class AnanUserPermissionRespDto extends IdDto<Long> implements Serializable {
    private static final long serialVersionUID = -28245693115711034L;
    @ApiModelProperty(value = "机构ID", example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户ID", example = "Long")
    private Long userId;

    @ApiModelProperty(value = "权限ID", example = "Long")
    private Long permissionId;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", example = "Integer")
    private Integer addMode;

}
