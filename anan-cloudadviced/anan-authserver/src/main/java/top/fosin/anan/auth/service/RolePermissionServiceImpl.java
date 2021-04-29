package top.fosin.anan.auth.service;

import top.fosin.anan.auth.service.inter.RolePermissionService;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.platformapi.entity.AnanRolePermissionEntity;
import top.fosin.anan.platformapi.repository.RolePermissionRepository;

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
    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_ROLE_PERMISSION, key = "#roleId")
    public List<AnanRolePermissionEntity> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }
}
