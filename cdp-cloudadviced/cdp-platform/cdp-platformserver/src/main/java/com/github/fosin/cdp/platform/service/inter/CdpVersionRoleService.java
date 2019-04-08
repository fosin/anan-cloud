package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.platform.dto.request.CdpVersionRoleCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRoleRetrieveDto;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRoleUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionRoleEntity;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;

/**
 * 系统版本角色表(cdp_version_role)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface CdpVersionRoleService extends ISimpleJpaService<CdpVersionRoleEntity, Long, CdpVersionRoleCreateDto, CdpVersionRoleRetrieveDto, CdpVersionRoleUpdateDto> {
}
