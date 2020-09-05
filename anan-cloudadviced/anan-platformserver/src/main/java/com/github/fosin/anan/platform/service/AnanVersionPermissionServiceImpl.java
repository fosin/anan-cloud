package com.github.fosin.anan.platform.service;

import com.github.fosin.anan.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.anan.platform.dto.request.AnanVersionPermissionUpdateDto;
import com.github.fosin.anan.platform.entity.AnanVersionPermissionEntity;
import com.github.fosin.anan.platform.repository.AnanVersionPermissionRepository;
import com.github.fosin.anan.platform.service.inter.AnanVersionPermissionService;
import com.github.fosin.anan.util.BeanUtil;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 系统版本权限表(anan_version_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class AnanVersionPermissionServiceImpl implements AnanVersionPermissionService {
    private final AnanVersionPermissionRepository ananSysVersionPermissionRepository;

    public AnanVersionPermissionServiceImpl(AnanVersionPermissionRepository ananSysVersionPermissionRepository) {
        this.ananSysVersionPermissionRepository = ananSysVersionPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanVersionPermissionRepository getRepository() {
        return ananSysVersionPermissionRepository;
    }

    @Override
    public List<AnanVersionPermissionEntity> findByVersionId(Long versionId) {
        return getRepository().findByVersionId(versionId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public Collection<AnanVersionPermissionEntity> updateInBatch(Long versionId, Collection<AnanVersionPermissionUpdateDto> entities) {

        Assert.notNull(versionId, "传入的版本ID不能为空!");

        for (AnanVersionPermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getVersionId().equals(versionId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        ananSysVersionPermissionRepository.deleteByVersionId(versionId);

        Collection<AnanVersionPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return ananSysVersionPermissionRepository.saveAll(saveEntities);
    }

}
