package com.github.fosin.cdp.platform.service.inter;

import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.jpa.service.ISimpleJpaService;
import com.github.fosin.cdp.platformapi.dto.request.CdpRoleCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpRoleRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpRoleUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpRoleEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IRoleService extends ISimpleJpaService<CdpRoleEntity, Long, CdpRoleCreateDto, CdpRoleRetrieveDto, CdpRoleUpdateDto> {

    List<CdpRoleEntity> findOtherUsersByRoleId(Long userId);

    List<CdpRoleEntity> findRoleUsersByRoleId(Long userId);

    Result findAllByOrganizId(Long organizId, PageModule pageModule);

    List<CdpRoleEntity> findAllByOrganizId(Long organizId);
}
