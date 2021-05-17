package top.fosin.anan.platform.service.inter;

import top.fosin.anan.platform.dto.res.AnanOrganizationPermissionRespDto;
import top.fosin.anan.jpa.service.batch.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionCreateDto;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionRetrieveDto;
import top.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;

import java.util.List;

/**
 * 系统机构权限表(anan_organization_permission)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanOrganizationPermissionService extends
        ICrudBatchJpaService<AnanOrganizationPermissionEntity, AnanOrganizationPermissionRespDto, Long, Long,
                AnanOrganizationPermissionCreateDto, AnanOrganizationPermissionRetrieveDto> {
    List<AnanOrganizationPermissionRespDto> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

}
