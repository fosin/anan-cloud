package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.platform.dto.res.AnanOrganizationPermissionRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionCreateDto;
import top.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import top.fosin.anan.platform.repository.AnanOrganizationPermissionRepository;
import top.fosin.anan.platform.service.inter.AnanOrganizationPermissionService;

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
    private final AnanOrganizationPermissionRepository organizationPermissionRepository;

    public AnanOrganizationPermissionServiceImpl(AnanOrganizationPermissionRepository organizationPermissionRepository) {
        this.organizationPermissionRepository = organizationPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public AnanOrganizationPermissionRepository getRepository() {
        return organizationPermissionRepository;
    }

    @Override
    public List<AnanOrganizationPermissionRespDto> findByOrganizId(Long organizId) {
        return BeanUtil.copyCollectionProperties(getRepository().findByOrganizId(organizId), AnanOrganizationPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public List<AnanOrganizationPermissionRespDto> updateInBatch(String deleteCol, Long organizId, Collection<AnanOrganizationPermissionCreateDto> dtos) {
        Assert.notNull(organizId, "传入的版本ID不能为空!");

        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getOrganizId().equals(organizId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        Collection<AnanOrganizationPermissionEntity> saveEntities = BeanUtil.copyCollectionProperties(dtos, AnanOrganizationPermissionEntity.class);

        organizationPermissionRepository.deleteByOrganizId(organizId);

        return BeanUtil.copyCollectionProperties(organizationPermissionRepository.saveAll(saveEntities), AnanOrganizationPermissionRespDto.class);
    }
}
