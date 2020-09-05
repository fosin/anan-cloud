package com.github.fosin.anan.platform.service.inter;

import com.github.fosin.anan.platform.dto.request.AnanVersionRoleCreateDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionRoleRetrieveDto;
import com.github.fosin.anan.platform.dto.request.AnanVersionRoleUpdateDto;
import com.github.fosin.anan.platform.entity.AnanVersionRoleEntity;
import com.github.fosin.anan.jpa.service.ISimpleJpaService;

/**
 * 系统版本角色表(anan_version_role)表服务接口
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
public interface AnanVersionRoleService extends ISimpleJpaService<AnanVersionRoleEntity, Long, AnanVersionRoleCreateDto, AnanVersionRoleRetrieveDto, AnanVersionRoleUpdateDto> {
}
