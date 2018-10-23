package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpSysRoleEntity;
import com.github.fosin.cdp.mvc.service.ISimpleService;

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
