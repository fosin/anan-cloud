package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.anan.platform.dto.request.AnanVersionRolePermissionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanVersionRolePermissionEntity;
import com.github.fosin.anan.platform.repository.AnanVersionRolePermissionRepository;
import com.github.fosin.anan.platform.service.inter.AnanVersionRolePermissionService;
import com.github.fosin.anan.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 系统版本角色权限表(anan_version_role_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanVersionRolePermissionServiceImpl implements AnanVersionRolePermissionService {
    private final AnanVersionRolePermissionRepository versionRolePermissionRepository;

    public AnanVersionRolePermissionServiceImpl(AnanVersionRolePermissionRepository versionRolePermissionRepository) {
        this.versionRolePermissionRepository = versionRolePermissionRepository;
    }

    /**
     * 获取DAOs
     */
    @Override
    public AnanVersionRolePermissionRepository getRepository() {
        return versionRolePermissionRepository;
    }

    @Override
    public List<AnanVersionRolePermissionEntity> findByRoleId(Long roleId) {
        return getRepository().findByRoleId(roleId);
    }

    @Override
    @Transactional
    public List<AnanVersionRolePermissionEntity> updateInBatch(Long roleId, Collection<AnanVersionRolePermissionUpdateDto> entities) {

        Assert.notNull(roleId, "传入的版本ID不能为空!");

        for (AnanVersionRolePermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        versionRolePermissionRepository.deleteByRoleId(roleId);

        Collection<AnanVersionRolePermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return versionRolePermissionRepository.saveAll(saveEntities);
    }
}
