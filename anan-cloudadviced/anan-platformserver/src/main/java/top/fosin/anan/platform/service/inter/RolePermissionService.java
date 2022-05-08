package top.fosin.anan.platform.service.inter;


import top.fosin.anan.cloudresource.dto.res.AnanRolePermissionRespDto;
import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanRolePermissionReqDto;
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
                Long, AnanRolePermissionReqDto,
                AnanRolePermissionReqDto> {
    List<AnanRolePermissionEntity> findByRoleId(Long roleId);

    long countByPermissionId(Long permissionId);
}
