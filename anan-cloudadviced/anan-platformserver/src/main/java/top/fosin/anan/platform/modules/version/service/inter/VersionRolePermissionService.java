package top.fosin.anan.platform.modules.version.service.inter;

import top.fosin.anan.platform.modules.pub.service.inter.AnanPermissionService;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionReqDto;
import top.fosin.anan.platform.modules.version.dto.VersionRolePermissionRespDto;
import top.fosin.anan.platform.modules.version.entity.VersionRolePermission;

/**
 * 系统版本角色权限系统版本角色权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface VersionRolePermissionService extends AnanPermissionService<VersionRolePermissionReqDto, VersionRolePermissionRespDto, Long, VersionRolePermission
        > {
}
