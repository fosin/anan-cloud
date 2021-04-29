package top.fosin.anan.platform.service;

import top.fosin.anan.jpa.service.batch.IUpdateInBatchJpaService;
import top.fosin.anan.platform.dto.request.AnanOrganizationPermissionUpdateDto;
import top.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import top.fosin.anan.platform.repository.AnanOrganizationPermissionRepository;
import top.fosin.anan.platform.service.inter.AnanOrganizationPermissionService;
import top.fosin.anan.core.util.BeanUtil;

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
    private final AnanOrganizationPermissionRepository ananSysOrganizationPermissionRepository;

    public AnanOrganizationPermissionServiceImpl(AnanOrganizationPermissionRepository ananSysOrganizationPermissionRepository) {
        this.ananSysOrganizationPermissionRepository = ananSysOrganizationPermissionRepository;
    }

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

        Assert.isTrue(entities.stream().allMatch(entity -> entity.getOrganizId().equals(organizId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        ananSysOrganizationPermissionRepository.deleteByOrganizId(organizId);

        Collection<AnanOrganizationPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(this.getClass(), IUpdateInBatchJpaService.class, entities);

        return ananSysOrganizationPermissionRepository.saveAll(saveEntities);
    }
}
