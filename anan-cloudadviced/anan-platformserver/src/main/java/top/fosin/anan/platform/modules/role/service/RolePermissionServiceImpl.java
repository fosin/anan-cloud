package top.fosin.anan.platform.modules.role.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.entity.res.RolePermissionRespDTO;
import top.fosin.anan.platform.modules.role.dao.RolePermissionDao;
import top.fosin.anan.platform.modules.role.dto.RolePermissionReqDto;
import top.fosin.anan.platform.modules.role.service.inter.RolePermissionService;

import java.util.Collection;
import java.util.List;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@Lazy
@AllArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionDao rolePermissionDao;

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, allEntries = true),
                    @CacheEvict(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, allEntries = true)
            })
    @Transactional(rollbackFor = Exception.class)
    public List<RolePermissionRespDTO> processInBatch(Long roleId, Collection<RolePermissionReqDto> dtos, boolean... processAction) {
        return RolePermissionService.super.processInBatch(roleId, dtos, processAction);
    }

    @Override
    public RolePermissionDao getDao() {
        return rolePermissionDao;
    }

}
