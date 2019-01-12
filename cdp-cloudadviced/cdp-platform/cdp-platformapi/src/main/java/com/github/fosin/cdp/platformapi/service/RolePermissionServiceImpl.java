package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.platformapi.repository.RolePermissionRepository;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.entity.CdpSysRolePermissionEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.service.inter.IRolePermissionService;
import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
@Lazy
public class RolePermissionServiceImpl implements IRolePermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_ROLE_PERMISSION, key = "#roleId")
    public List<CdpSysRolePermissionEntity> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return rolePermissionRepository.countByPermissionId(permissionId);
    }

    @Override
    public List<CdpSysRolePermissionEntity> createInBatch(Collection<CdpSysRolePermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        for (CdpSysRolePermissionEntity entity : entities) {
            entity.setCreateBy(loginUser.getId());
            entity.setCreateTime(now);
        }
        return rolePermissionRepository.save(entities);
    }

    @Override
    public void deleteInBatch(Collection<CdpSysRolePermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        Set<Long> needDelRoles = new HashSet<>();
        for (CdpSysRolePermissionEntity entity : entities) {
            needDelRoles.add(entity.getRoleId());
        }
        Assert.notEmpty(needDelRoles, "没有找到需要删除数据!");
        for (Long roleId : needDelRoles) {
            CacheUtil.evict(TableNameConstant.CDP_SYS_ROLE_PERMISSION, roleId + "");
        }
        rolePermissionRepository.deleteInBatch(entities);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        for (Long roleId : needDelRoles) {
            CacheUtil.evict(TableNameConstant.CDP_SYS_ROLE_PERMISSION, roleId + "");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_ROLE_PERMISSION, key = "#roleId")
            }
    )
    @Transactional
    public List<CdpSysRolePermissionEntity> updateInBatch(Long roleId, Collection<CdpSysRolePermissionEntity> entities) {
        Assert.notNull(roleId, "传入的角色ID不能为空!");

        for (CdpSysRolePermissionEntity entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与角色ID不匹配的数据!");
        }

        rolePermissionRepository.deleteByRoleId(roleId);
        if (entities.iterator().hasNext()) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            Date now = new Date();
            for (CdpSysRolePermissionEntity entity : entities) {
                entity.setCreateBy(loginUser.getId());
                entity.setCreateTime(now);
            }
            return rolePermissionRepository.save(entities);
        }

        return null;
    }

    @Override
    public IJpaRepository<CdpSysRolePermissionEntity, Long> getRepository() {
        return rolePermissionRepository;
    }
}
