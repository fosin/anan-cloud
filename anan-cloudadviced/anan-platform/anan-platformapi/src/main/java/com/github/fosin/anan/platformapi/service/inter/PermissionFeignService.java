package com.github.fosin.anan.platformapi.service.inter;

import com.github.fosin.anan.platformapi.constant.ServiceConstant;
import com.github.fosin.anan.platformapi.constant.UrlPrefixConstant;
import com.github.fosin.anan.platformapi.service.PermissionFeignFallbackServiceImpl;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient(value = ServiceConstant.ANAN_AUTHSERVER, path = UrlPrefixConstant.PERMISSION, fallback = PermissionFeignFallbackServiceImpl.class)
public interface PermissionFeignService {

    /**
     * 远程查询应用权限
     *
     * @param appName 应用名称
     * @return 应用权限列表
     */
    @PostMapping("/findByAppName/{appName}")
    ResponseEntity<List<AnanPermissionEntity>> findByAppName(@PathVariable("appName") String appName);
}
