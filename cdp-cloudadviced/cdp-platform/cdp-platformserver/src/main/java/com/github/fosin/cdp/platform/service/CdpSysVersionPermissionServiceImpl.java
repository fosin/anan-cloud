package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.jpa.util.JpaUtil;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionPermissionCreateDto;
import com.github.fosin.cdp.platform.dto.request.CdpSysVersionPermissionUpdateDto;
import com.github.fosin.cdp.platform.entity.CdpSysVersionPermissionEntity;
import com.github.fosin.cdp.platform.repository.CdpSysVersionPermissionRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysVersionPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * 系统版本权限表(cdp_sys_version_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysVersionPermissionServiceImpl implements ICdpSysVersionPermissionService {
    @Autowired
    private CdpSysVersionPermissionRepository cdpSysVersionPermissionRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysVersionPermissionRepository getRepository() {
        return cdpSysVersionPermissionRepository;
    }

    @Override
    public List<CdpSysVersionPermissionEntity> findByVersionId(Long versionId) {
        return getRepository().findByVersionId(versionId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public Collection<CdpSysVersionPermissionEntity> updateInBatch(Long versionId, Collection<CdpSysVersionPermissionUpdateDto> entities) {

        Assert.notNull(versionId, "传入的版本ID不能为空!");

        for (CdpSysVersionPermissionUpdateDto entity : entities) {
            Assert.isTrue(entity.getVersionId().equals(versionId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        cdpSysVersionPermissionRepository.deleteByVersionId(versionId);

        Collection<CdpSysVersionPermissionEntity> saveEntities = JpaUtil.copyCollectionProperties(this.getClass(), entities);

        return cdpSysVersionPermissionRepository.save(saveEntities);
    }

}