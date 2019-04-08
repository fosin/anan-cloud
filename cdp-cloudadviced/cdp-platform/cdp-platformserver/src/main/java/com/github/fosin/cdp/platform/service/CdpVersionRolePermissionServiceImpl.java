package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpVersionRolePermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionRolePermissionEntity;
import com.github.fosin.cdp.platform.repository.CdpVersionRolePermissionRepository;
import com.github.fosin.cdp.platform.service.inter.CdpVersionRolePermissionService;
import com.github.fosin.cdp.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 系统版本角色权限表(cdp_version_role_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpVersionRolePermissionServiceImpl implements CdpVersionRolePermissionService {
    @Autowired
    private CdpVersionRolePermissionRepository versionRolePermissionRepository;

    /**
     * 获取DAOs
     */
    @Override
    public CdpVersionRolePermissionRepository getRepository() {
        return versionRolePermissionRepository;
    }

    @Override
    public List<CdpVersionRolePermissionEntity> findByRoleId(Long roleId) {
        return getRepository().findByRoleId(roleId);
    }

    @Override
    @Transactional
    public List<CdpVersionRolePermissionEntity> updateInBatch(Long roleId, Collection<CdpVersionRolePermissionUpdateDto> entities) {

        Assert.notNull(roleId, "传入的版本ID不能为空!");

        for (CdpVersionRolePermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        versionRolePermissionRepository.deleteByRoleId(roleId);

        Collection<CdpVersionRolePermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return versionRolePermissionRepository.saveAll(saveEntities);
    }
}
