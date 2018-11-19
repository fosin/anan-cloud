package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.mvc.module.PageModule;
import com.github.fosin.cdp.mvc.result.Result;
import com.github.fosin.cdp.mvc.service.ISimpleService;
import com.github.fosin.cdp.platformapi.entity.CdpSysRoleEntity;

import java.util.List;

/**
 *  2017/12/29.
 * Time:12:30
 * @author fosin
 */
public interface IRoleService extends ISimpleService<CdpSysRoleEntity,Long> {

    List<CdpSysRoleEntity> findOtherUsersByRoleId(Long userId);

    List<CdpSysRoleEntity> findRoleUsersByRoleId(Long userId);

    Result findAllByOrganizId(Long organizId, PageModule pageModule);
}
