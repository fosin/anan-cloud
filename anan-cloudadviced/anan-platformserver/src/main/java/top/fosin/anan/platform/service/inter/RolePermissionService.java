package top.fosin.anan.platform.service.inter;


import top.fosin.anan.platform.dto.request.AnanRolePermissionCreateDto;
import top.fosin.anan.platform.dto.request.AnanRolePermissionRetrieveDto;
import top.fosin.anan.cloudresource.dto.res.AnanRolePermissionRespDto;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.entity.AnanRolePermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
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
