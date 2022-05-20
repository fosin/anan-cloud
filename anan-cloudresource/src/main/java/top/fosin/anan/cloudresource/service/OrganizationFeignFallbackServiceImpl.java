package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.res.OrganizationRespDto;
import top.fosin.anan.cloudresource.service.inter.OrganizationFeignService;
import top.fosin.anan.model.result.MultResult;
import top.fosin.anan.model.result.SingleResult;

import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
@Service
public class OrganizationFeignFallbackServiceImpl implements OrganizationFeignService {

    @Override
    public MultResult<OrganizationRespDto> listChild(Long pid) {
        log.error("feign 通过机构pid远程获取子组织机构权限失败:{}", pid);
        return null;
    }

    @Override
    public MultResult<OrganizationRespDto> findAllByIds(List<Long> ids) {
        log.error("feign 远程获取机构信息失败:{}", ids);
        return null;
    }

    @Override
    public MultResult<OrganizationRespDto> listAllChild(Long pid) {
        log.error("feign 通过机构pid远程获取所有组织机构失败:{}", pid);
        return null;
    }

    @Override
    public MultResult<OrganizationRespDto> tree(Long topId) {
        log.error("feign 通过机构topId远程获取组织机构失败:{}", topId);
        return null;
    }

    @Override
    public SingleResult<OrganizationRespDto> findOneById(Long id) {
        log.error("feign 通过机构ID远程查询一个组织机构失败:{}", id);
        return null;
    }

}
