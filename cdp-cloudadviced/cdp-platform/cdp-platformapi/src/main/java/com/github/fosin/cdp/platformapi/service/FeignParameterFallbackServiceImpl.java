package com.github.fosin.cdp.platformapi.service;


import com.github.fosin.cdp.platformapi.dto.request.CdpParameterCreateDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterRetrieveDto;
import com.github.fosin.cdp.platformapi.dto.request.CdpParameterUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpParameterEntity;
import com.github.fosin.cdp.platformapi.service.inter.IFeignParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class FeignParameterFallbackServiceImpl implements IFeignParameterService {

    @Override
    public ResponseEntity<CdpParameterEntity> create(CdpParameterCreateDto cdpParameterCreateDto) {
        log.error("feign 远程创建通用系统参数失败:{}", cdpParameterCreateDto);
        return null;
    }

    @Override
    public ResponseEntity<CdpParameterEntity> update(CdpParameterUpdateDto cdpParameterUpdateDto) {
        log.error("feign 远程更新通用系统参数失败:{}", cdpParameterUpdateDto);
        return null;
    }

    @Override
    public ResponseEntity<CdpParameterEntity> getParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}", type, scope, name);
        return null;
    }

    @Override
    public ResponseEntity<String> getOrCreateParameter(CdpParameterRetrieveDto retrieveDto) {
        log.error("feign 远程查询通用系统参数失败:{}", retrieveDto);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> apply(Long id) {
        log.error("feign 远程应用系统参数失败:{}", id);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> applys() {
        log.error("feign 远程应用所有系统参数失败");
        return null;
    }
}
