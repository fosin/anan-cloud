package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.cache.util.CacheUtil;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.jpa.repository.IJpaRepository;
import com.github.fosin.cdp.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.cdp.platform.service.inter.UserPermissionService;
import com.github.fosin.cdp.platformapi.constant.TableNameConstant;
import com.github.fosin.cdp.platformapi.dto.request.CdpUserPermissionUpdateDto;
import com.github.fosin.cdp.platformapi.entity.CdpUserPermissionEntity;
import com.github.fosin.cdp.platformapi.repository.UserPermissionRepository;
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
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    @Cacheable(value = TableNameConstant.CDP_USER_PERMISSION, key = "T(String).valueOf(#userId).concat('-').concat(T(String).valueOf(#organizId))")
    public List<CdpUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return userPermissionRepository.findByUserIdAndOrganizId(userId, organizId);
    }

    @Override
    @Cacheable(value = TableNameConstant.CDP_USER_PERMISSION, key = "#userId")
    public List<CdpUserPermissionEntity> findByUserId(Long userId) {
        return userPermissionRepository.findByUserId(userId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return userPermissionRepository.countByPermissionId(permissionId);
    }

    @Override
    public void deleteInBatch(Collection<CdpUserPermissionEntity> entities) {
        Set<Long> needDelUsers = new HashSet<>();
        for (CdpUserPermissionEntity entity : entities) {
            needDelUsers.add(entity.getUserId());
        }
        Assert.isTrue(needDelUsers.size() != 0, "没有找到需要删除数据!");
        for (Long userId : needDelUsers) {
            CacheUtil.evict(TableNameConstant.CDP_USER_PERMISSION, userId + "");
        }
        userPermissionRepository.deleteInBatch(entities);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new CdpServiceException(e);
        }
        for (Long userId : needDelUsers) {
            CacheUtil.evict(TableNameConstant.CDP_USER_PERMISSION, userId + "");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = TableNameConstant.CDP_USER_PERMISSION, key = "#userId")
            }
    )
    @Transactional
    public List<CdpUserPermissionEntity> updateInBatch(Long userId, Collection<CdpUserPermissionUpdateDto> entities) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        for (CdpUserPermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getUserId().equals(userId), "需要更新的数据集中有与用户ID不匹配的数据!");
        }

        Collection<CdpUserPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        userPermissionRepository.deleteByUserId(userId);

        return userPermissionRepository.saveAll(saveEntities);
    }

    @Override
    public IJpaRepository<CdpUserPermissionEntity, Long> getRepository() {
        return userPermissionRepository;
    }
}
