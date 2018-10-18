package com.starlight.cdp.platformapi.service.inter;


import com.starlight.cdp.core.exception.CdpServiceException;
import com.starlight.cdp.core.exception.CdpUserOrPassInvalidException;
import com.starlight.cdp.mvc.service.ICrudBatchService;
import com.starlight.cdp.platformapi.entity.CdpSysUserRoleEntity;

import java.util.List;

/**
 *  2017/12/29.
 * Time:12:30
 * @author fosin
 */
public interface IUserRoleService extends ICrudBatchService<CdpSysUserRoleEntity,Integer>{
    List<CdpSysUserRoleEntity> findByUserId(Integer userId);

    List<CdpSysUserRoleEntity> findByRoleId(Integer roleId);

    List<CdpSysUserRoleEntity> findByUsercodeAndPassword(String usercode, String password) throws CdpUserOrPassInvalidException;

    List<CdpSysUserRoleEntity> updateInBatchByUserId(Integer userId, Iterable<CdpSysUserRoleEntity> entitis) throws CdpServiceException;

    List<CdpSysUserRoleEntity> updateInBatchByRoleId(Integer roleId, Iterable<CdpSysUserRoleEntity> entitis) throws CdpServiceException;
}
