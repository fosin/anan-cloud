package top.fosin.anan.zuul.service.inter;

import top.fosin.anan.model.constant.PathConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import top.fosin.anan.zuul.service.PermissionFeignFallbackServiceImpl;
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
     * @param serviceCode 服务标识
     * @return 应用权限列表
     */
    @PostMapping("/serviceCode/{serviceCode}")
    ResponseEntity<List<AnanPermissionRetrieveDto>> findByServiceCode(@PathVariable("serviceCode") String serviceCode);

    /**
     * 远程查询应用权限
     *
     * @return 应用权限列表
     */
    @PostMapping(PathConstant.PATH_LIST)
    ResponseEntity<List<AnanPermissionRetrieveDto>> findAll();

}
