package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.entity.req.ParameterCreateDTO;
import top.fosin.anan.cloudresource.entity.res.ParameterDTO;
import top.fosin.anan.cloudresource.entity.req.ParameterUpdateDTO;
import top.fosin.anan.cloudresource.service.inter.feign.ParameterFeignService;
import top.fosin.anan.data.result.SingleResult;

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
    public SingleResult<ParameterDTO> processCreate(ParameterCreateDTO reqDto) {
        log.error("feign 远程创建通用系统参数失败:{}", reqDto);
        return null;
    }

    @Override
    public void processUpdate(ParameterUpdateDTO reqDto) {
        log.error("feign 远程更新通用系统参数失败:{}", reqDto);
    }

    @Override
    public SingleResult<ParameterDTO> getParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}{}{}", type, scope, name);
        return null;
    }

    @Override
    public SingleResult<ParameterDTO> getNearestParameter(Integer type, String scope, String name) {
        log.error("feign 远程查询通用系统参数失败:{}{}{}", type, scope, name);
        return null;
    }

    @Override
    public SingleResult<String> getOrCreateParameter(ParameterUpdateDTO reqDto) {
        log.error("feign 远程查询通用系统参数失败:{}", reqDto);
        return null;
    }

    @Override
    public SingleResult<Boolean> applyChange(Long id) {
        log.error("feign 远程应用系统参数失败:{}", id);
        return null;
    }

    @Override
    public SingleResult<Boolean> applyChangeAll() {
        log.error("feign 远程应用所有系统参数失败");
        return null;
    }

    @Override
    public SingleResult<Boolean> applyChanges(List<Long> ids) {
        log.error("feign 远程应用所有系统参数失败:{}", ids);
        return null;
    }

    @Override
    public void cancelDelete(Collection<Long> ids) {
        log.error("feign 远程取消删除系统参数失败:{}", ids);
    }
}
