package top.fosin.anan.platform.modules.version.service.inter;

import top.fosin.anan.platform.modules.permission.service.inter.PermissionBaseService;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionUpdateDTO;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionDTO;
import top.fosin.anan.platform.modules.version.po.VersionRolePermission;

/**
 * 系统版本角色权限系统版本角色权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionRolePermissionService extends PermissionBaseService<VersionRolePermissionUpdateDTO, VersionRolePermissionDTO, Long, VersionRolePermission> {
}
