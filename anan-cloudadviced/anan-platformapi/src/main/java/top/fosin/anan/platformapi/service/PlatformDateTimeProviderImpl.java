package top.fosin.anan.platformapi.service;

import org.springframework.data.auditing.DateTimeProvider;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * Description:
 *
 * @author fosin
 * @date 2019.1.17
 */
public class PlatformDateTimeProviderImpl implements DateTimeProvider {

    @Override
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now());
    }
}
