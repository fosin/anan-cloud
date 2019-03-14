package com.github.fosin.cdp.seulth;

import com.github.fosin.cdp.SleuthServerApplication;
import com.github.fosin.cdp.core.banner.CdpBanner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author fosin
 */
public class CdpZipkinServerInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SleuthServerApplication.class).banner(new CdpBanner("CDP Sleuth Server")).logStartupInfo(true);
    }
}
