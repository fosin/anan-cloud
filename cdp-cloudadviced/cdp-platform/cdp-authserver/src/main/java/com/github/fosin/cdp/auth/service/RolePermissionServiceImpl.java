package com.github.fosin.cdp.auth.service;

import com.github.fosin.cdp.auth.service.inter.RolePermissionService;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpRolePermissionEntity;
import com.github.fosin.cdp.platformapi.repository.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_ROLE_PERMISSION, key = "#roleId")
    public List<CdpRolePermissionEntity> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }
}
