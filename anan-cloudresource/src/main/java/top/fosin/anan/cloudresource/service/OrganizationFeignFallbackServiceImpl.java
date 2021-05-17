package top.fosin.anan.cloudresource.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.fosin.anan.cloudresource.dto.res.AnanOrganizationRespDto;
import top.fosin.anan.cloudresource.service.inter.OrganizationFeignService;

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
    public ResponseEntity<List<AnanOrganizationRespDto>> listChild(Long pid) {
        log.error("feign 通过机构pid远程获取子组织机构权限失败:{}", pid);
        return null;
    }

    @Override
    public ResponseEntity<List<AnanOrganizationRespDto>> findAllByIds(List<Long> ids) {
        log.error("feign 远程获取机构信息失败:{}", ids);
        return null;
    }

    @Override
    public ResponseEntity<List<AnanOrganizationRespDto>> listAllChild(Long pid) {
        log.error("feign 通过机构pid远程获取所有组织机构失败:{}", pid);
        return null;
    }

    @Override
    public ResponseEntity<List<AnanOrganizationRespDto>> tree(Long topId) {
        log.error("feign 通过机构topId远程获取组织机构失败:{}", topId);
        return null;
    }

    @Override
    public ResponseEntity<AnanOrganizationRespDto> findOne(Long id) {
        log.error("feign 通过机构ID远程查询一个组织机构失败:{}", id);
        return null;
    }

}
