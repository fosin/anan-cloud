package top.fosin.anan.cloudresource.service.inter.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.FieldConstant;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.constant.PathSuffixConstant;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.entity.res.PermissionRespDTO;
import top.fosin.anan.cloudresource.service.PermissionFeignFallbackServiceImpl;
import top.fosin.anan.data.result.MultResult;

import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = PathPrefixConstant.PERMISSION,
        fallback = PermissionFeignFallbackServiceImpl.class, contextId = "permissionFeignService")
public interface PermissionFeignService {

    /**
     * 远程查询应用权限
     *
     * @param serviceCode 服务标识
     * @param version     api版本号
     * @return 应用权限列表
     */
    @GetMapping(PathSuffixConstant.SERVICE_CODE_VARIBALE)
    MultResult<PermissionRespDTO> findByServiceCode(@PathVariable(FieldConstant.SERVICE_CODE) String serviceCode, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);

    /**
     * 远程查询应用权限
     *
     * @param serviceCodes 服务标识清单
     * @param version      api版本号
     * @return 应用权限列表
     */
    @PostMapping(value = PathSuffixConstant.SERVICE_CODES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MultResult<PermissionRespDTO> findByServiceCodes(@RequestBody List<String> serviceCodes, @RequestParam(PathPrefixConstant.API_VERSION_NAME) String version);
}
