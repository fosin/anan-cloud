package com.github.fosin.anan.platformapi.service;

import com.github.fosin.anan.platformapi.util.LoginUserUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Description:
 *
 * @author fosin
 * @date 2019.1.15
 */
@Configuration
public class UserIdAuditorImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(LoginUserUtil.getUser().getId());
    }
}
