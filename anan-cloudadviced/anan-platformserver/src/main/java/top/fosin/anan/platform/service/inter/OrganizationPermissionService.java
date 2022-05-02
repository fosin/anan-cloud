package top.fosin.anan.platform.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionReqDto;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionReqDto;
import top.fosin.anan.platform.dto.res.AnanOrganizationPermissionRespDto;
import top.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;

import java.util.List;

/**
 * 系统机构权限系统机构权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrganizationPermissionService extends
        ICrudBatchJpaService<AnanOrganizationPermissionEntity, AnanOrganizationPermissionRespDto, Long, Long,
                AnanOrganizationPermissionReqDto, AnanOrganizationPermissionReqDto> {
    List<AnanOrganizationPermissionRespDto> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

}
