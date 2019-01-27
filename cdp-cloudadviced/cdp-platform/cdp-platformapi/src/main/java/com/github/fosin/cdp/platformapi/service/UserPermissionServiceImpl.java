package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.jpa.util.JpaUtil;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpSysUserPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpSysRolePermissionEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserPermissionEntity;
import com.github.fosin.cdp.platformapi.repository.UserPermissionRepository;
import com.github.fosin.cdp.platformapi.service.inter.IUserPermissionService;
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
public class UserPermissionServiceImpl implements IUserPermissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_USER_PERMISSION, key = "T(String).valueOf(#userId).concat('-').concat(T(String).valueOf(#organizId))")
    public List<CdpSysUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return userPermissionRepository.findByUserIdAndOrganizId(userId, organizId);
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_SYS_USER_PERMISSION, key = "#userId")
    public List<CdpSysUserPermissionEntity> findByUserId(Long userId) {
        return userPermissionRepository.findByUserId(userId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return userPermissionRepository.countByPermissionId(permissionId);
    }

    @Override
    public void deleteInBatch(Collection<CdpSysUserPermissionEntity> entities) {
        Set<Long> needDelUsers = new HashSet<>();
        for (CdpSysUserPermissionEntity entity : entities) {
            needDelUsers.add(entity.getUserId());
        }
        Assert.isTrue(needDelUsers.size() != 0, "没有找到需要删除数据!");
        for (Long userId : needDelUsers) {
            CacheUtil.evict(TableNameConstant.CDP_SYS_USER_PERMISSION, userId + "");
        }
        userPermissionRepository.deleteInBatch(entities);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        for (Long userId : needDelUsers) {
            CacheUtil.evict(TableNameConstant.CDP_SYS_USER_PERMISSION, userId + "");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_SYS_USER_PERMISSION, key = "#userId")
            }
    )
    @Transactional
    public List<CdpSysUserPermissionEntity> updateInBatch(Long userId, Collection<CdpSysUserPermissionUpdateDto> entities) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        for (CdpSysUserPermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getUserId().equals(userId), "需要更新的数据集中有与用户ID不匹配的数据!");
        }

        Collection<CdpSysUserPermissionEntity> saveEntities = JpaUtil.copyCollectionProperties(this.getClass(), entities);

        userPermissionRepository.deleteByUserId(userId);

        return userPermissionRepository.save(saveEntities);
    }

    @Override
    public IJpaRepository<CdpSysUserPermissionEntity, Long> getRepository() {
        return userPermissionRepository;
    }
}
