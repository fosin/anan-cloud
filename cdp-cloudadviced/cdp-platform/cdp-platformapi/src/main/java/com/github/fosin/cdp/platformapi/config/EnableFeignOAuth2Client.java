package com.github.fosin.cdp.platformapi.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.lang.annotation.*;

/**
 * @author fosin
 * @date 2019/3/27
 */
@Import({OAuth2FeignConfigure.class})
//@EnableOAuth2Client
@EnableFeignClients
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnableFeignOAuth2Client {
}
