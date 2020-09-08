package com.github.fosin.anan.zuul.service.inter;

import com.github.fosin.anan.model.constant.PathConstant;
import com.github.fosin.anan.cloudresource.constant.ServiceConstant;
import com.github.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import com.github.fosin.anan.zuul.service.PermissionFeignFallbackServiceImpl;
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
    @PostMapping("/appName/{appName}")
    ResponseEntity<List<AnanPermissionRetrieveDto>> findByAppName(@PathVariable("appName") String appName);

    /**
     * 远程查询应用权限
     *
     * @return 应用权限列表
     */
    @PostMapping(PathConstant.PATH_LIST)
    ResponseEntity<List<AnanPermissionRetrieveDto>> findAll();

}
