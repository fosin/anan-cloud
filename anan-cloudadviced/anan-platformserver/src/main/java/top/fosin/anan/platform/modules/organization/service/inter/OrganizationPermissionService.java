package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationPermission;

import java.util.List;

/**
 * 系统机构权限系统机构权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrganizationPermissionService extends
        ICrudBatchJpaService<OrganizationPermission, OrganizationPermissionRespDto, Long, Long,
                OrganizationPermissionReqDto, OrganizationPermissionReqDto> {
    List<OrganizationPermissionRespDto> findByOrganizId(Long organizId);

    long countByPermissionId(Long permissionId);

}
