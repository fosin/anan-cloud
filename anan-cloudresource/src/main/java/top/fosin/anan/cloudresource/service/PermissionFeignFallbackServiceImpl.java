package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.res.AnanPermissionRespDto;
import top.fosin.anan.cloudresource.service.inter.PermissionFeignService;

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
    public ResponseEntity<List<AnanPermissionRespDto>> findByServiceCode(String serviceCode) {
        log.error("feign 远程查询当前应用权限失败:{}", serviceCode);
        return null;
    }
}
