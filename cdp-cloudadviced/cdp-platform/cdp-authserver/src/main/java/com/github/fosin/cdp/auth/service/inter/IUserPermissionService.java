package com.github.fosin.cdp.auth.service.inter;


import com.github.fosin.cdp.platformapi.entity.CdpUserPermissionEntity;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:37
 *
 * @author fosin
 */
public interface IUserPermissionService {
    List<CdpUserPermissionEntity> findByUserId(Long userId);
}
