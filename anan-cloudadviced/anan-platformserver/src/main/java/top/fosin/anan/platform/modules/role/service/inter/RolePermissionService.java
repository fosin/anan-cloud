package top.fosin.anan.platform.modules.role.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.role.dto.RolePermissionDTO;
import top.fosin.anan.platform.modules.role.dto.RolePermissionUpdateDTO;
import top.fosin.anan.platform.modules.role.po.RolePermission;

/**
 * 系统角色权限表(anan_role_permission)服务类
 *
 * @author fosin
 * @date 2023-05-14
 */
public interface RolePermissionService extends
        ICrudBatchJpaService<RolePermissionUpdateDTO, RolePermissionDTO, Long, RolePermission> {
}

