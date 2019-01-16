package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.constant.SystemConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * Description:
 *
 * @author fosin
 * @date 2019.1.15
 */
@Configuration
public class UserIdAuditorImpl implements AuditorAware<Long> {
    @Override
    public Long getCurrentAuditor() {
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        return loginUser == null ? SystemConstant.SUPER_USER_ID : loginUser.getId();
    }
}
