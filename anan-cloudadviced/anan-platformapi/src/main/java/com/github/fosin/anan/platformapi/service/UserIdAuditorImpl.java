package com.github.fosin.anan.platformapi.service;

import com.github.fosin.anan.cloudresource.util.AnanUserDetailUtil;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserIdAuditorImpl implements AuditorAware<Long> {
    private final AnanUserDetailUtil ananUserDetailUtil;

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(ananUserDetailUtil.getAnanUser().getId());
    }
}
