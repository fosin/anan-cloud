package com.github.fosin.cdp.platformapi.service;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Description:
 *
 * @author fosin
 * @date 2019.1.17
 */
public class PlatformDateTimeProviderImpl implements DateTimeProvider {

    @Override
    public Calendar getNow() {
        return new GregorianCalendar();
    }
}
