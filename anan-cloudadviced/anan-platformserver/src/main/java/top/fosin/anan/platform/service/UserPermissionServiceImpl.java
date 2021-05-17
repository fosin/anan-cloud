package top.fosin.anan.platform.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platform.dto.req.AnanUserPermissionCreateDto;
import top.fosin.anan.platform.dto.res.AnanUserPermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.repository.UserPermissionRepository;
import top.fosin.anan.platform.service.inter.UserPermissionService;
import top.fosin.anan.redis.cache.AnanCacheManger;

import java.util.Collection;
import java.util.List;

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
    public List<AnanUserPermissionRespDto> findByUserIdAndOrganizId(Long userId, Long organizId) {
        return BeanUtil.copyCollectionProperties(
                userPermissionRepository.findByUserIdAndOrganizId(userId, organizId),AnanUserPermissionRespDto.class);
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER_PERMISSION, key = "#userId")
    public List<AnanUserPermissionRespDto> findByUserId(Long userId) {
        return BeanUtil.copyCollectionProperties(
                userPermissionRepository.findByUserId(userId), AnanUserPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return userPermissionRepository.countByPermissionId(permissionId);
    }

//    @Override
//    public void deleteInBatch(Collection<AnanUserPermissionEntity> entities) {
//        Set<Long> needDelUsers = new HashSet<>();
//        for (AnanUserPermissionEntity entity : entities) {
//            needDelUsers.add(entity.getUserId());
//        }
//        Assert.isTrue(needDelUsers.size() != 0, "没有找到需要删除数据!");
//        for (Long userId : needDelUsers) {
//            ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
//            ananCacheManger.evict(RedisConstant.ANAN_USER_PERMISSION, userId + "");
//        }
//        userPermissionRepository.deleteInBatch(entities);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new AnanServiceException(e);
//        }
//        for (Long userId : needDelUsers) {
//            ananCacheManger.evict(RedisConstant.ANAN_USER_ALL_PERMISSIONS, userId + "");
//            ananCacheManger.evict(RedisConstant.ANAN_USER_PERMISSION, userId + "");
//        }
//    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = RedisConstant.ANAN_USER_PERMISSION, key = "#userId"),
                    @CacheEvict(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId"),
            }
    )
    @Transactional
    public Collection<AnanUserPermissionRespDto> updateInBatch(String deleteCol, Long userId, Collection<AnanUserPermissionCreateDto> dtos) {
        return UserPermissionService.super.updateInBatch(deleteCol, userId, dtos);
    }

    @Override
    public UserPermissionRepository getRepository() {
        return userPermissionRepository;
    }
}
