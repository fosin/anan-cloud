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
 * 系统用户角色表(anan_user_role)DTO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统用户角色表(anan_user_role)DTO")
public class UserRoleDTO extends Id<Long> {
    private static final long serialVersionUID = 841899805227236995L;
    @Schema(description = "机构ID", example = "Long")
    private Long organizId;

    @Schema(description = "用户ID", example = "Long")
    private Long userId;

    @Schema(description = "角色ID", example = "Long")
    private Long roleId;

    @DateTimeFormat(pattern = DateTimeUtil.DATETIME_PATTERN)
    @Schema(description = "创建日期", example = "Date")
    private Date createTime;

    @Schema(description = "创建人", example = "Long")
    private Long createBy;

}
