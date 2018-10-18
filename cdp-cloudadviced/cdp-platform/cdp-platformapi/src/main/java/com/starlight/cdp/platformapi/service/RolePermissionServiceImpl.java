package com.starlight.cdp.platformapi.service;

import com.starlight.cdp.core.exception.CdpServiceException;
import com.starlight.cdp.platformapi.constant.TableNameConstant;
import com.starlight.cdp.platformapi.entity.CdpSysRolePermissionEntity;
import com.starlight.cdp.platformapi.entity.CdpSysUserEntity;
import com.starlight.cdp.platformapi.repository.RolePermissionRepository;
import com.starlight.cdp.platformapi.service.inter.IRolePermissionService;
import com.starlight.cdp.cache.util.CacheUtil;
import com.starlight.cdp.platformapi.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
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
public class RolePermissionServiceImpl implements IRolePermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_ROLE_PERMISSION, key = "#roleId")
    public List<CdpSysRolePermissionEntity> findByRoleId(Integer roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public long countByPermissionId(Integer permissionId) {
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
        Set<Integer> needDelRoles = new HashSet<>();
        for (CdpSysRolePermissionEntity entity : entities) {
            needDelRoles.add(entity.getRoleId());
        }
        Assert.notEmpty(needDelRoles, "没有找到需要删除数据!");
        for (Integer roleId : needDelRoles) {
            CacheUtil.evict(TableNameConstant.CDP_SYS_ROLE_PERMISSION, roleId + "");
        }
        rolePermissionRepository.deleteInBatch(entities);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        for (Integer roleId : needDelRoles) {
            CacheUtil.evict(TableNameConstant.CDP_SYS_ROLE_PERMISSION, roleId + "");
        }
    }

    @Override
    public Collection<CdpSysRolePermissionEntity> updateInBatch(Collection<CdpSysRolePermissionEntity> entities) {
        throw new CdpServiceException("该方法还没有实现!");
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_ROLE_PERMISSION, key = "#roleId")
            }
    )
    @Transactional
    public List<CdpSysRolePermissionEntity> updateInBatch(Integer roleId, Collection<CdpSysRolePermissionEntity> entities) {
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
}
