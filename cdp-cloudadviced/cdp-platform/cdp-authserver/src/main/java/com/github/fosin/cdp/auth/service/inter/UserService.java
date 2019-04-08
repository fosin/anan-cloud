package com.github.fosin.cdp.auth.service.inter;

import com.github.fosin.cdp.platformapi.entity.CdpUserEntity;

/**
 * 2017/12/27.
 * Time:15:12
 *
 * @author fosin
 */
public interface UserService {
    CdpUserEntity findByUsercode(String usercode);

}
