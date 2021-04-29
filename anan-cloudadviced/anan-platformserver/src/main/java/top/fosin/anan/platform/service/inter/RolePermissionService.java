package top.fosin.anan.platform.service.inter;


import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.cloudresource.dto.request.AnanRolePermissionCreateDto;
import top.fosin.anan.cloudresource.dto.request.AnanRolePermissionRetrieveDto;
import top.fosin.anan.cloudresource.dto.request.AnanRolePermissionUpdateDto;
import top.fosin.anan.platformapi.entity.AnanRolePermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface RolePermissionService extends ICrudBatchJpaService<AnanRolePermissionEntity, Long, Long, AnanRolePermissionCreateDto, AnanRolePermissionRetrieveDto, AnanRolePermissionUpdateDto> {
    List<AnanRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
