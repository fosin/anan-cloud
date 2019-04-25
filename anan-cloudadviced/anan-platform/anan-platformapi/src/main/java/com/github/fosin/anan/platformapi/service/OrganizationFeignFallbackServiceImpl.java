package com.github.fosin.anan.platformapi.service;


import com.github.fosin.anan.platformapi.entity.AnanOrganizationEntity;
import com.github.fosin.anan.platformapi.service.inter.OrganizationFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * 远程调用权限服务
 *
 * @author fosin
 * @date 2019-3-26
 */
@Slf4j
//@Service
public class OrganizationFeignFallbackServiceImpl implements OrganizationFeignService {

    @Override
    public ResponseEntity<List<AnanOrganizationEntity>> listChild(Long pId) {
        log.error("feign 远程获取组织机构权限失败:{}", pId);
        return null;
    }

    @Override
    public ResponseEntity<List<AnanOrganizationEntity>> listAllChild(Long pId) {
        log.error("feign 远程获取组织机构权限失败:{}", pId);
        return null;
    }

    @Override
    public ResponseEntity<List<AnanOrganizationEntity>> tree(Long topId) {
        log.error("feign 远程获取组织机构权限失败:{}", topId);
        return null;
    }

    @Override
    public ResponseEntity<AnanOrganizationEntity> findOne(Long aLong) {
        log.error("feign 远程查询一个组织机构失败:{}", aLong);
        return null;
    }

}
