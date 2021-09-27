package top.fosin.anan.cloudresource.service.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.fosin.anan.cloudresource.constant.RequestPath;
import top.fosin.anan.cloudresource.constant.ServiceConstant;
import top.fosin.anan.cloudresource.constant.UrlPrefixConstant;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.service.PermissionFeignFallbackServiceImpl;

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
     * @return 应用权限列表
     */
    @PostMapping(RequestPath.SERVICE_CODE)
    ResponseEntity<List<AnanPermissionRespDto>> findByServiceCode(@NotBlank @PathVariable("serviceCode") String serviceCode);

    /**
     * 远程查询应用权限
     *
     * @param serviceCodes 服务标识清单
     * @return 应用权限列表
     */
    @PostMapping(value = RequestPath.SERVICE_CODES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AnanPermissionRespDto>> findByServiceCodes(@NotEmpty @RequestBody List<String> serviceCodes);
}
