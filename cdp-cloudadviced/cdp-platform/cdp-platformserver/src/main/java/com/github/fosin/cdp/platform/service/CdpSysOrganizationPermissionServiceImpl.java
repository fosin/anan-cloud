package com.github.fosin.cdp.platform.service;

import com.github.fosin.cdp.platform.entity.CdpSysOrganizationPermissionEntity;
import com.github.fosin.cdp.platform.repository.CdpSysOrganizationPermissionRepository;
import com.github.fosin.cdp.platform.service.inter.ICdpSysOrganizationPermissionService;
import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
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
 * 系统机构权限表(cdp_sys_organization_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysOrganizationPermissionServiceImpl implements ICdpSysOrganizationPermissionService {
    @Autowired
    private CdpSysOrganizationPermissionRepository cdpSysOrganizationPermissionRepository;

    /**
     * 获取DAO
     */
    @Override
    public CdpSysOrganizationPermissionRepository getRepository() {
        return cdpSysOrganizationPermissionRepository;
    }

    @Override
    public List<CdpSysOrganizationPermissionEntity> findByOrganizId(Long organizId) {
        return getRepository().findByOrganizId(organizId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public List<CdpSysOrganizationPermissionEntity> updateInBatch(Long organizId, Collection<CdpSysOrganizationPermissionEntity> entities) {
        Assert.notNull(organizId, "传入的版本ID不能为空!");

        for (CdpSysOrganizationPermissionEntity entity : entities) {
            Assert.isTrue(entity.getOrganizId().equals(organizId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        getRepository().deleteByOrganizId(organizId);
        if (entities.iterator().hasNext()) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            Date now = new Date();
            for (CdpSysOrganizationPermissionEntity entity : entities) {
                entity.setCreateBy(loginUser.getId());
                entity.setCreateTime(now);
            }
            return getRepository().save(entities);
        }

        return null;
    }

    @Override
    public Collection<CdpSysOrganizationPermissionEntity> createInBatch(Collection<CdpSysOrganizationPermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        for (CdpSysOrganizationPermissionEntity entity : entities) {
            entity.setCreateBy(loginUser.getId());
            entity.setCreateTime(now);
        }
        return getRepository().save(entities);
    }

    @Override
    public void deleteInBatch(Collection<CdpSysOrganizationPermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        getRepository().deleteInBatch(entities);
    }
}