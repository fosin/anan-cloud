package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.jpa.service.ICrudBatchJpaService;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionDTO;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionUpdateDTO;
import top.fosin.anan.platform.modules.organization.po.OrganizationPermission;

/**
 * 系统机构权限系统机构权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrgPermissionService extends
        ICrudBatchJpaService<OrganizationPermissionUpdateDTO, OrganizationPermissionDTO, Long, OrganizationPermission> {
}
