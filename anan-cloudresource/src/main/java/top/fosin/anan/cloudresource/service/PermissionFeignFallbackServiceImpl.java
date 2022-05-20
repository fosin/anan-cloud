package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.res.PermissionRespDto;
import top.fosin.anan.cloudresource.service.inter.PermissionFeignService;
import top.fosin.anan.model.result.MultResult;

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
    public MultResult<PermissionRespDto> findByServiceCode(String serviceCode) {
        log.error("feign 远程查询当前应用权限失败:{}", serviceCode);
        return null;
    }

    /**
     * 远程查询应用权限
     *
     * @param serviceCodes 服务标识清单
     * @return 应用权限列表
     */
    @Override
    public MultResult<PermissionRespDto> findByServiceCodes(List<String> serviceCodes) {
        log.error("feign 远程批量查询当前应用权限失败:{}", serviceCodes);
        return null;
    }
}
