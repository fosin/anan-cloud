package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import top.fosin.anan.cloudresource.constant.PathPrefixConstant;
import top.fosin.anan.cloudresource.entity.res.PermissionRespDTO;
import top.fosin.anan.cloudresource.service.inter.feign.PermissionFeignService;
import top.fosin.anan.data.result.MultResult;

import java.util.List;

/**
 * 远程调用权限服务-熔断类
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class PermissionFeignFallbackServiceImpl implements PermissionFeignService {

    @Override
    public MultResult<PermissionRespDTO> findByServiceCode(String serviceCode, @RequestParam(value = PathPrefixConstant.API_VERSION_NAME) String version) {
        log.error("feign 远程查询当前应用权限失败:{}", serviceCode);
        return null;
    }

    /**
     * 远程查询应用权限
     *
     * @param serviceCodes 服务标识清单
     * @param version
     * @return 应用权限列表
     */
    @Override
    public MultResult<PermissionRespDTO> findByServiceCodes(List<String> serviceCodes, @RequestParam(value = PathPrefixConstant.API_VERSION_NAME) String version) {
        log.error("feign 远程批量查询当前应用权限失败:{}", serviceCodes);
        return null;
    }
}
