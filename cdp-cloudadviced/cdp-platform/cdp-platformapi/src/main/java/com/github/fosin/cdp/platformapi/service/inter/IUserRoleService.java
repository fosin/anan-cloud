package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.entity.CdpSysUserRoleEntity;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.core.exception.CdpUserOrPassInvalidException;
import com.github.fosin.cdp.jpa.service.batch.ICrudBatchJpaService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:30
 *
 * @author fosin
 */
public interface IUserRoleService extends ICrudBatchJpaService<CdpSysUserRoleEntity, Long, Long, CdpSysUserRoleEntity, CdpSysUserRoleEntity, CdpSysUserRoleEntity> {
    List<CdpSysUserRoleEntity> findByUserId(Long userId);

    List<CdpSysUserRoleEntity> findByRoleId(Long roleId);

    List<CdpSysUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws CdpUserOrPassInvalidException;

    List<CdpSysUserRoleEntity> updateInBatchByUserId(Long userId, Iterable<CdpSysUserRoleEntity> entitis) throws CdpServiceException;

    List<CdpSysUserRoleEntity> updateInBatchByRoleId(Long roleId, Iterable<CdpSysUserRoleEntity> entitis) throws CdpServiceException;
}
