package top.fosin.anan.platform.modules.user.vo;



import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

        /**
 * 系统用户角色表(anan_user_role)单体VO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统用户角色表(anan_user_role)单体VO")
public class UserRoleVO extends Id<Long> {
    private static final long serialVersionUID = -89148019053982944L;
    @Schema(description = "机构ID")
    private Long organizId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "角色ID")
    private Long roleId;

}
