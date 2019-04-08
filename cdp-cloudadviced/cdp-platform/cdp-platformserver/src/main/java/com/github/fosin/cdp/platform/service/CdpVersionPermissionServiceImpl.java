package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.jpa.service.batch.IUpdateInBatchJpaService;
import com.github.fosin.cdp.platform.dto.request.CdpVersionPermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpVersionPermissionEntity;
import com.github.fosin.cdp.platform.repository.CdpVersionPermissionRepository;
import com.github.fosin.cdp.platform.service.inter.CdpVersionPermissionService;
import com.github.fosin.cdp.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 系统版本权限表(cdp_version_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpVersionPermissionServiceImpl implements CdpVersionPermissionService {
    @Autowired
    private CdpVersionPermissionRepository cdpSysVersionPermissionRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpVersionPermissionRepository getRepository() {
        return cdpSysVersionPermissionRepository;
    }

    @Override
    public List<CdpVersionPermissionEntity> findByVersionId(Long versionId) {
        return getRepository().findByVersionId(versionId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public Collection<CdpVersionPermissionEntity> updateInBatch(Long versionId, Collection<CdpVersionPermissionUpdateDto> entities) {

        Assert.notNull(versionId, "传入的版本ID不能为空!");

        for (CdpVersionPermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getVersionId().equals(versionId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        cdpSysVersionPermissionRepository.deleteByVersionId(versionId);

        Collection<CdpVersionPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return cdpSysVersionPermissionRepository.saveAll(saveEntities);
    }

}
