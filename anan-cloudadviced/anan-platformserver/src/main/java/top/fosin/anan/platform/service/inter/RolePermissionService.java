package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.res.AnanRolePermissionRespDto;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanRolePermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanRolePermissionRetrieveDto;
import top.fosin.anan.platform.entity.AnanRolePermissionEntity;

import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 *
 */
public interface RolePermissionService extends
        ICrudBatchJpaService<AnanRolePermissionEntity,
                AnanRolePermissionRespDto,
                Long,
                Long, AnanRolePermissionCreateDto,
                AnanRolePermissionRetrieveDto> {
    List<AnanRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
