package com.github.fosin.cdp.platform.service.inter;


import com.github.fosin.cdp.platformapi.dto.request.CdpUserRoleCreateDto;
import com.github.fosin.cdp.platformapi.entity.CdpUserRoleEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.core.exception.CdpUserOrPassInvalidException;
import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;

import java.util.Collection;
import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IUserRoleService extends ICrudBatchJpaService<CdpUserRoleEntity, Long, Long, CdpUserRoleCreateDto, CdpUserRoleCreateDto, CdpUserRoleCreateDto> {
    List<CdpUserRoleEntity> findByUserId(Long userId);

    List<CdpUserRoleEntity> findByRoleId(Long roleId);

    List<CdpUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws CdpUserOrPassInvalidException;

    List<CdpUserRoleEntity> updateInBatchByUserId(Long userId, Collection<CdpUserRoleCreateDto> entitis);

    List<CdpUserRoleEntity> updateInBatchByRoleId(Long roleId, Collection<CdpUserRoleCreateDto> entitis);
}
