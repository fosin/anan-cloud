package top.fosin.anan.platform.modules.version.vo;

                                                                                            

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
 * 系统版本角色权限表(anan_version_role_permission)单体VO
 *
 * @author fosin
 * @date 2023-05-13
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统版本角色权限表(anan_version_role_permission)单体VO")
public class VersionRolePermissionVO extends Id<Long> {
    private static final long serialVersionUID = 573653096278544292L;
    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "权限ID")
    private Long permissionId;

}
