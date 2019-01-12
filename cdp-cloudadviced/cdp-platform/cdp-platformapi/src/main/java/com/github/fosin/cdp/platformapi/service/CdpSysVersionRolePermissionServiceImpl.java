package com.github.fosin.cdp.platformapi.service;

import com.github.fosin.cdp.platformapi.entity.CdpSysUserEntity;
import com.github.fosin.cdp.platformapi.entity.CdpSysVersionRolePermissionEntity;
import com.github.fosin.cdp.platformapi.repository.CdpSysVersionRolePermissionRepository;
import com.github.fosin.cdp.platformapi.service.inter.ICdpSysVersionRolePermissionService;
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
 * 系统版本角色权限表(cdp_sys_version_role_permission)表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class CdpSysVersionRolePermissionServiceImpl implements ICdpSysVersionRolePermissionService {
    @Autowired
    private CdpSysVersionRolePermissionRepository versionRolePermissionRepository;

    /**
     * 获取DAOs
     */
    @Override
    public CdpSysVersionRolePermissionRepository getRepository() {
        return versionRolePermissionRepository;
    }
    @Override
    public List<CdpSysVersionRolePermissionEntity> findByRoleId(Long roleId) {
        return getRepository().findByRoleId(roleId);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public List<CdpSysVersionRolePermissionEntity> updateInBatch(Long roleId, Collection<CdpSysVersionRolePermissionEntity> entities) {

        Assert.notNull(roleId, "传入的版本ID不能为空!");

        for (CdpSysVersionRolePermissionEntity entity : entities) {
            Assert.isTrue(entity.getRoleId().equals(roleId), "需要更新的数据集中有与版本ID不匹配的数据!");
        }

        getRepository().deleteByRoleId(roleId);
        if (entities.iterator().hasNext()) {
            CdpSysUserEntity loginUser = LoginUserUtil.getUser();
            Date now = new Date();
            for (CdpSysVersionRolePermissionEntity entity : entities) {
                entity.setCreateBy(loginUser.getId());
                entity.setCreateTime(now);
            }
            return getRepository().save(entities);
        }


        return null;
    }

    @Override
    public Collection<CdpSysVersionRolePermissionEntity> createInBatch(Collection<CdpSysVersionRolePermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        CdpSysUserEntity loginUser = LoginUserUtil.getUser();
        Date now = new Date();
        for (CdpSysVersionRolePermissionEntity entity : entities) {
            entity.setCreateBy(loginUser.getId());
            entity.setCreateTime(now);
        }
        return getRepository().save(entities);
    }

    @Override
    public void deleteInBatch(Collection<CdpSysVersionRolePermissionEntity> entities) {
        Assert.notEmpty(entities, "要删除的集合不能为空!");
        getRepository().deleteInBatch(entities);
    }

}