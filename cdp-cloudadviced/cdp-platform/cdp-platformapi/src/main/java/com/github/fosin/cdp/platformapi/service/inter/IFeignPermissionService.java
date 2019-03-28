package com.github.fosin.cdp.platformapi.service.inter;

import com.github.fosin.cdp.platformapi.constant.ServiceConstant;
import com.github.fosin.cdp.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.cdp.platformapi.service.FeignPermissionFallbackServiceImpl;
import com.github.fosin.cdp.platformapi.entity.CdpPermissionEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(value = ServiceConstant.CDP_AUTHSERVER, path = UrlPrefixConstant.PERMISSION, fallback = FeignPermissionFallbackServiceImpl.class)
public interface IFeignPermissionService {

    /**
     * 远程查询应用权限
     *
     * @param appName 应用名称
     * @return 应用权限列表
     */
    @PostMapping("/findByAppName/{appName}")
    ResponseEntity<List<CdpPermissionEntity>> findByAppName(@PathVariable("appName") String appName);
}
