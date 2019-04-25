package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.anan.platform.dto.request.AnanOrganizationPermissionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import com.github.fosin.anan.platform.repository.AnanOrganizationPermissionRepository;
import com.github.fosin.anan.platform.service.inter.AnanOrganizationPermissionService;
import com.github.fosin.anan.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 系统机构权限表(anan_organization_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanOrganizationPermissionServiceImpl implements AnanOrganizationPermissionService {
    @Autowired
    private AnanOrganizationPermissionRepository ananSysOrganizationPermissionRepository;

    /**
     * 获取DAO
     */
    @Override
    public AnanOrganizationPermissionRepository getRepository() {
        return ananSysOrganizationPermissionRepository;
    }

    @Override
    public List<AnanOrganizationPermissionEntity> findByOrganizId(Long organizId) {
        return getRepository().findByOrganizId(organizId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public List<AnanOrganizationPermissionEntity> updateInBatch(Long organizId, Collection<AnanOrganizationPermissionUpdateDto> entities) {
        Assert.notNull(organizId, "传入的版本ID不能为空!");

        for (AnanOrganizationPermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getOrganizId().equals(organizId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        ananSysOrganizationPermissionRepository.deleteByOrganizId(organizId);

        Collection<AnanOrganizationPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return ananSysOrganizationPermissionRepository.saveAll(saveEntities);
    }
}
