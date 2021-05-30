package top.fosin.anan.cloudresource.service;

import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

/**
 * @author fosin
 * @date 2019.1.17
 */
public class PlatformDateTimeProviderImpl implements DateTimeProvider {

    @Override
    @NonNull
    public Optional<TemporalAccessor> getNow() {
        return Optional.of(LocalDateTime.now());
    }
}
