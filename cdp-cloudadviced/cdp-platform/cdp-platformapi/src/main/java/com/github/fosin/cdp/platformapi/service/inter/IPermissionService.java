package com.github.fosin.cdp.platformapi.service.inter;


import com.github.fosin.cdp.platformapi.entity.CdpSysPermissionEntity;
import com.github.fosin.cdp.mvc.service.ISimpleService;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IPermissionService extends ISimpleService<CdpSysPermissionEntity, Integer> {
    List<CdpSysPermissionEntity> findByPId(Integer pId);

    List<CdpSysPermissionEntity> findByType(Integer type);

    List<CdpSysPermissionEntity> findByAppName(String appName);
}
