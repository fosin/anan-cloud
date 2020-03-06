package com.github.fosin.anan.zuulgateway.ratelimit;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;
import org.springframework.cloud.netflix.zuul.filters.Route;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义限流规则
 *
 * @author fosin
 * @date 2020/3/2
 * @since 2.0.0
 */
//@Component
public class AnanRateLimitKeyGenerator extends DefaultRateLimitKeyGenerator {

    public AnanRateLimitKeyGenerator(RateLimitProperties properties, RateLimitUtils rateLimitUtils) {
        super(properties, rateLimitUtils);
    }

    //在这个方法里面写限流逻辑
    @Override
    public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
        return super.key(request, route, policy);
    }
}
