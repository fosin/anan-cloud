package top.fosin.anan.platform.modules.user.dto;



import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "用于增减用户的单项权限，通常实在角色的基础上增减单项权限(anan_user_permission)DTO")
public class UserPermissionDTO extends Id<Long> {
    private static final long serialVersionUID = -98940357408205359L;
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @Schema(description = "用户ID", example = "Long")
    private Long userId;

    @Schema(description = "权限ID", example = "Long")
    private Long permissionId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

    @Schema(description = "补充方式：0=增加权限、1=删除权限", example = "Integer")
    private Integer addMode;

}
