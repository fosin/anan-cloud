package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpSysVersionRoleCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionRoleRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionRoleUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpSysVersionRoleEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统版本角色表(cdp_sys_version_role)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface ICdpSysVersionRoleService extends ISimpleJpaService<CdpSysVersionRoleEntity, Long, CdpSysVersionRoleCreateDto, CdpSysVersionRoleRetrieveDto, CdpSysVersionRoleUpdateDto> {
}