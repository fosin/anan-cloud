package com.github.fosin.anan.platformapi.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

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
