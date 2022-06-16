package top.fosin.anan.platform.modules.organization.service.inter;

import top.fosin.anan.platform.modules.organization.dto.OrgPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationPermission;
import top.fosin.anan.platform.modules.pub.service.inter.AnanPermissionService;

/**
 * 系统机构权限系统机构权限表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface OrgPermissionService extends
        AnanPermissionService<OrgPermissionReqDto, OrgPermissionRespDto, Long, OrganizationPermission> {
}
