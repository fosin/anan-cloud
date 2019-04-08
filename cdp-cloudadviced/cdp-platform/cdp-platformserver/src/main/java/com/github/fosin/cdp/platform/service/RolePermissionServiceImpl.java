package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.cdp.platform.service.inter.RolePermissionService;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpRolePermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpRolePermissionEntity;
import com.github.fosin.cdp.platformapi.repository.RolePermissionRepository;
import com.github.fosin.cdp.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
@Lazy
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_ROLE_PERMISSION, key = "#roleId")
    public List<CdpRolePermissionEntity> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return rolePermissionRepository.countByPermissionId(permissionId);
    }

    @Override
    public void deleteInBatch(Collection<CdpRolePermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        Set<Long> needDelRoles = new HashSet<>();
        for (CdpRolePermissionEntity entity : entities) {
            needDelRoles.add(entity.getRoleId());
        }
        Assert.notEmpty(needDelRoles, "没有找到需要删除数据!");
        for (Long roleId : needDelRoles) {
            CacheUtil.evict(TableNameConstant.CDP_ROLE_PERMISSION, roleId + "");
        }
        rolePermissionRepository.deleteInBatch(entities);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        for (Long roleId : needDelRoles) {
            CacheUtil.evict(TableNameConstant.CDP_ROLE_PERMISSION, roleId + "");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_ROLE_PERMISSION, key = "#roleId")
            }
    )
    @Transactional
    public List<CdpRolePermissionEntity> updateInBatch(Long roleId, Collection<CdpRolePermissionUpdateDto> entities) {
        Assert.notNull(roleId, "传入的角色ID不能为空!");

        for (CdpRolePermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与角色ID不匹配的数据!");
        }
        Collection<CdpRolePermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        rolePermissionRepository.deleteByRoleId(roleId);
        return rolePermissionRepository.saveAll(saveEntities);
    }

    @Override
    public IJpaRepository<CdpRolePermissionEntity, Long> getRepository() {
        return rolePermissionRepository;
    }
}
