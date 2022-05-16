package top.fosin.anan.platform.modules.role.service.inter;


import top.fosin.anan.cloudresource.dto.res.RolePermissionRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.role.dto.RolePermissionReqDto;
import top.fosin.anan.platform.modules.role.entity.RolePermission;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface RolePermissionService extends
        ICrudBatchJpaService<RolePermission,
                RolePermissionRespDto,
                Long,
                Long, RolePermissionReqDto,
                RolePermissionReqDto> {
    List<RolePermission> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
