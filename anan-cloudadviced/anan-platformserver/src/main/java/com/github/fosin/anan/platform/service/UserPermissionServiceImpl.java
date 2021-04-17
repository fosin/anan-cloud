package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.core.exception.AnanServiceException;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.anan.platform.service.inter.UserPermissionService;
import com.github.fosin.anan.cloudresource.constant.RedisConstant;
import com.github.fosin.anan.platformapi.entity.AnanUserPermissionEntity;
import com.github.fosin.anan.platformapi.repository.UserPermissionRepository;
import com.github.fosin.anan.cloudresource.dto.request.AnanUserPermissionUpdateDto;
import com.github.fosin.anan.redis.cache.AnanCacheManger;
import com.github.fosin.anan.core.util.BeanUtil;
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
    private final UserPermissionRepository userPermissionRepository;
    private final AnanCacheManger ananCacheManger;

    public UserPermissionServiceImpl(UserPermissionRepository userPermissionRepository, AnanCacheManger ananCacheManger) {
        this.userPermissionRepository = userPermissionRepository;
        this.ananCacheManger = ananCacheManger;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER_PERMISSION, key = "T(String).valueOf(#userId).concat('-').concat(T(String).valueOf(#organizId))")
    public List<AnanUserPermissionEntity> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return userPermissionRepository.findByUserIdAndOrganizId(userId, organizId);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER_PERMISSION, key = "#userId")
    public List<AnanUserPermissionEntity> findByUserId(Long userId) {
        return userPermissionRepository.findByUserId(userId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return userPermissionRepository.countByPermissionId(permissionId);
    }

    @Override
    public void deleteInBatch(Collection<AnanUserPermissionEntity> entities) {
        Set<Long> needDelUsers = new HashSet<>();
        for (AnanUserPermissionEntity entity : entities) {
            needDelUsers.add(entity.getUserId());
        }
        Assert.isTrue(needDelUsers.size() != 0, "没有找到需要删除数据!");
        for (Long userId : needDelUsers) {
            ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
            ananCacheManger.evict(RedisConstant.ANAN_USER_PERMISSION, userId + "");
        }
        userPermissionRepository.deleteInBatch(entities);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new AnanServiceException(e);
        }
        for (Long userId : needDelUsers) {
            ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
            ananCacheManger.evict(RedisConstant.ANAN_USER_PERMISSION, userId + "");
        }
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, key = "#userId"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId"),
            }
    )
    @Transactional
    public List<AnanUserPermissionEntity> updateInBatch(Long userId, Collection<AnanUserPermissionUpdateDto> entities) {
        Assert.notNull(userId, "传入的用户ID不能为空!");
        Assert.isTrue(entities.stream().allMatch(entity -> entity.getUserId().equals(userId)), "需要更新的数据集中有与用户ID不匹配的数据!");

        Collection<AnanUserPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        userPermissionRepository.deleteByUserId(userId);

        return userPermissionRepository.saveAll(saveEntities);
    }

    @Override
    public IJpaRepository<AnanUserPermissionEntity, Long> getRepository() {
        return userPermissionRepository;
    }
}
