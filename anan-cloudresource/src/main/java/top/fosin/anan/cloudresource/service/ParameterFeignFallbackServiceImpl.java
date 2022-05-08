package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.req.AnanParameterReqDto;
import top.fosin.anan.cloudresource.dto.res.AnanParameterRespDto;
import top.fosin.anan.cloudresource.service.inter.ParameterFeignService;

import java.util.Collection;
import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class ParameterFeignFallbackServiceImpl implements ParameterFeignService {

    @Override
    public ResponseEntity<AnanParameterRespDto> create(AnanParameterReqDto AnanParameterReqDto) {
        log.error("feign 远程创建通用系统参数失败:{}", AnanParameterReqDto);
        return null;
    }

    @Override
    public ResponseEntity<AnanParameterRespDto> update(AnanParameterReqDto AnanParameterReqDto) {
        log.error("feign 远程更新通用系统参数失败:{}", AnanParameterReqDto);
        return null;
    }

    @Override
    public ResponseEntity<AnanParameterRespDto> getParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}{}{}", type, scope, name);
        return null;
    }

    @Override
    public ResponseEntity<AnanParameterRespDto> getNearestParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}{}{}", type, scope, name);
        return null;
    }

    @Override
    public ResponseEntity<String> getOrCreateParameter(AnanParameterReqDto retrieveDto) {
        log.error("feign 远程查询通用系统参数失败:{}", retrieveDto);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> applyChange(Long id) {
        log.error("feign 远程应用系统参数失败:{}", id);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> applyChangeAll() {
        log.error("feign 远程应用所有系统参数失败");
        return null;
    }

    @Override
    public ResponseEntity<Boolean> applyChangeAll(List<Long> ids) {
        log.error("feign 远程应用所有系统参数失败:{}", ids);
        return null;
    }

    @Override
    public void cancelDelete(Collection<Long> ids) {
        log.error("feign 远程取消删除系统参数失败:{}", ids);
    }
}
