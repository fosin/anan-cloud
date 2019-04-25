package com.github.fosin.anan.zuulgateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UrlPathHelper;

/**
 * Description
 *
 * @author fosin
 */
//@Component
@Slf4j
public class AccessMonitorFilter extends ZuulFilter {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ZuulProperties zuulProperties;

    private final UrlPathHelper urlPathHelper = new UrlPathHelper();
    private PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_FORWARD_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String url = request.getRequestURI();
        return url.contains("/admin")
                || url.contains("/eurekaServer")
                || url.contains("/sleuth")
                ;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = this.urlPathHelper.getPathWithinApplication(ctx.getRequest());

        for (String zuulRouteName : zuulProperties.getRoutes().keySet()) {
            ZuulProperties.ZuulRoute zuulRoute = zuulProperties.getRoutes().get(zuulRouteName);
            String path = zuulRoute.getPath();
            if (pathMatcher.match(path, requestURI)) {
                HttpHeaders headers = getHeaders(zuulRoute.getServiceId());
                //添加Basic Auth认证信息
                for (Map.Entry<String, List<String>> header : headers.entrySet()) {
                    ctx.addZuulRequestHeader(header.getKey(), header.getValue().get(0));
                }
            }
        }

        return null;
    }

    private String encode(String username, String password) {
        String token = Base64Utils
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        return "Basic " + token;
    }

    private HttpHeaders getHeaders(String serviceId) {
        HttpHeaders headers = new HttpHeaders();

        if (discoveryClient == null) {
            return headers;
        }

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
        if (serviceInstances.size() > 0) {
            ServiceInstance si = serviceInstances.get(0);
            String username = si.getMetadata().get("user.name");
            String password = si.getMetadata().get("user.password");
            if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                headers.set(HttpHeaders.AUTHORIZATION, encode(username, password));
            }
        }

        return headers;
    }
}