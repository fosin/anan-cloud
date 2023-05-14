package top.fosin.anan.platform.modules.user.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import top.fosin.anan.data.entity.Id;

import java.util.Date;
/**
 * 用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限DTO", description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)DTO")
public class UserPermissionDTO extends Id<Long> {
    private static final long serialVersionUID = -98940357408205359L;
    @ApiModelProperty(value = "机构ID", required = true, example = "Long")
    private Long organizId;

    @ApiModelProperty(value = "用户ID", required = true, example = "Long")
    private Long userId;

    @ApiModelProperty(value = "权限ID", required = true, example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @ApiModelProperty(value = "创建日期", required = true, example = "Date")
    private Date createTime;

    @ApiModelProperty(value = "创建人", required = true, example = "Long")
    private Long createBy;

    @ApiModelProperty(value = "补充方式：0=增加权限、1=删除权限", required = true, example = "Integer")
    private Integer addMode;

}
