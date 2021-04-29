package top.fosin.anan.auth.service;

import top.fosin.anan.auth.service.inter.UserPermissionService;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platformapi.entity.AnanUserPermissionEntity;
import top.fosin.anan.platformapi.repository.UserPermissionRepository;

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
public class UserPermissionServiceImpl implements UserPermissionService {
    private final UserPermissionRepository userPermissionRepository;

    public UserPermissionServiceImpl(UserPermissionRepository userPermissionRepository) {
        this.userPermissionRepository = userPermissionRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER_PERMISSION, key = "#userId")
    public List<AnanUserPermissionEntity> findByUserId(Long userId) {
        return userPermissionRepository.findByUserId(userId);
    }
}
