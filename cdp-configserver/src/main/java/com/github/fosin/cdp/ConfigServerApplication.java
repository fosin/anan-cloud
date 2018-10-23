package com.github.fosin.cdp;

import com.github.fosin.cdp.core.banner.CdpBanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.bootstrap.encrypt.RsaProperties;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

/**
 * Description
 * @author fosin
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerApplication.class)
                .banner(new CdpBanner("CDP Config Server"))
                .logStartupInfo(true)
                .run(args);
    }

    //TODO spring Cloud升级到EdgwareSR3后，启动报错需要一个bean RsaProperties，这里自己生成一个先
    @Bean
    @ConfigurationProperties("encrypt.rsa")
    public RsaProperties rsaProperties() {
        return new RsaProperties();
    }
}
