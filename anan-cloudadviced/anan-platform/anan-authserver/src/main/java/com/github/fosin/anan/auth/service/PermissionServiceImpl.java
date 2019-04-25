package com.github.fosin.anan.auth.service;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platformapi.constant.TableNameConstant;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.platformapi.repository.PermissionRepository;
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
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.ANAN_PERMISSION, key = "#id")
    public AnanPermissionEntity findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnanPermissionEntity> findByAppName(String appName) {
        return permissionRepository.findByAppName(appName);
    }

    @Override
    public IJpaRepository<AnanPermissionEntity, Long> getRepository() {
        return permissionRepository;
    }
}
