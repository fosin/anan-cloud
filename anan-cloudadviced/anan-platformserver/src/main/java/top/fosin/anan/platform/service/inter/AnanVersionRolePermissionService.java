package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.request.AnanVersionRolePermissionCreateDto;
import top.fosin.anan.platform.dto.request.AnanVersionRolePermissionRetrieveDto;
import top.fosin.anan.platform.dto.request.AnanVersionRolePermissionUpdateDto;
import top.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;

import java.util.List;

/**
 * 系统版本角色权限表(anan_version_role_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionRolePermissionService extends ICrudBatchJpaService<AnanVersionRolePermissionEntity,
        Long, Long, AnanVersionRolePermissionCreateDto, AnanVersionRolePermissionRetrieveDto, AnanVersionRolePermissionUpdateDto> {
    List<AnanVersionRolePermissionEntity> findByRoleId(Long roleId);
}
