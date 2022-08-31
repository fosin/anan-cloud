package top.fosin.anan.cloudresource.service.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.fosin.anan.cloudresource.constant.RequestPath;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.service.PermissionFeignFallbackServiceImpl;
import top.fosin.anan.data.result.MultResult;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@FeignClient(value = ServiceConstant.ANAN_PLATFORMSERVER, path = UrlPrefixConstant.PERMISSION,
        fallback = PermissionFeignFallbackServiceImpl.class, contextId = "permissionFeignService")
public interface PermissionFeignService {

    /**
     * 远程查询应用权限
     *
     * @param serviceCode 服务标识
     * @param version api版本号
     * @return 应用权限列表
     */
    @GetMapping(value = RequestPath.SERVICE_CODE)
    MultResult<PermissionRespDto> findByServiceCode(@NotBlank @PathVariable("serviceCode") String serviceCode, @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);

    /**
     * 远程查询应用权限
     *
     * @param serviceCodes 服务标识清单
     * @param version api版本号
     * @return 应用权限列表
     */
    @PostMapping(value = RequestPath.SERVICE_CODES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MultResult<PermissionRespDto> findByServiceCodes(@NotEmpty @RequestBody List<String> serviceCodes, @RequestParam(value = UrlPrefixConstant.API_VERSION_NAME) String version);
}
