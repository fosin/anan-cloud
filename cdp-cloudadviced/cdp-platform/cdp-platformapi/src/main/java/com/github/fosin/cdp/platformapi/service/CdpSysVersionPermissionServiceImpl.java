package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionPermissionEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysVersionPermissionRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionPermissionService;
import com.github.fosin.cdp.platformapi.util.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Date;
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
    protected CdpSysVersionPermissionRepository getRepository() {
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
    public List<CdpSysVersionPermissionEntity> updateInBatch(Long versionId, Collection<CdpSysVersionPermissionEntity> entities) {
        
        Assert.notNull(versionId, "传入的版本ID不能为空!");

        for (CdpSysVersionPermissionEntity entity : entities) {
            Assert.isTrue(entity.getVersionId().equals(versionId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        getRepository().deleteByVersionId(versionId);
        if (entities.iterator().hasNext()) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            Date now = new Date();
            for (CdpSysVersionPermissionEntity entity : entities) {
                entity.setCreateBy(loginUser.getId());
                entity.setCreateTime(now);
            }
            return getRepository().save(entities);
        }


        return null;
    }

    @Override
    public Collection<CdpSysVersionPermissionEntity> createInBatch(Collection<CdpSysVersionPermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        for (CdpSysVersionPermissionEntity entity : entities) {
            entity.setCreateBy(loginUser.getId());
            entity.setCreateTime(now);
        }
        return getRepository().save(entities);
    }

    @Override
    public void deleteInBatch(Collection<CdpSysVersionPermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        getRepository().deleteInBatch(entities);
    }

    @Override
    public Collection<CdpSysVersionPermissionEntity> updateInBatch(Collection<CdpSysVersionPermissionEntity> collection) {
        throw new CdpServiceException("该方法还没有实现!");
    }

}