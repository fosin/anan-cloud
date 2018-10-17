package com.starlight.cdp.platformapi.service.inter;

import com.starlight.cdp.mvc.service.ISimpleService;
import com.starlight.cdp.platformapi.entity.CdpSysRoleEntity;

import java.util.List;

/**
 *  2017/12/29.
 * Time:12:30
 * @author fosin
 */
public interface IRoleService extends ISimpleService<CdpSysRoleEntity,Integer> {

    List<CdpSysRoleEntity> findOtherUsersByRoleId(Integer userId);

    List<CdpSysRoleEntity> findRoleUsersByRoleId(Integer userId);
}
