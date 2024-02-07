package top.fosin.anan.platform.modules.role.vo;

                                                                                            

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import top.fosin.anan.core.util.DateTimeUtil;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.fosin.anan.data.entity.Id;

        /**
 * 系统角色权限表(anan_role_permission)单体VO
 *
 * @author fosin
 * @date 2023-05-14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色权限表(anan_role_permission)单体VO")
public class RolePermissionVO extends Id<Long> {
    private static final long serialVersionUID = 902298998580881113L;
    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "权限ID")
    private Long permissionId;

}
