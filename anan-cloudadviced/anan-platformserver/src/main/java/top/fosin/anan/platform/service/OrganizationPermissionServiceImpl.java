package top.fosin.anan.platform.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.dto.req.AnanOrganizationPermissionReqDto;
import top.fosin.anan.platform.dto.res.AnanOrganizationPermissionRespDto;
import top.fosin.anan.platform.entity.AnanOrganizationPermissionEntity;
import top.fosin.anan.platform.repository.OrganizationPermissionRepository;
import top.fosin.anan.platform.service.inter.OrganizationPermissionService;
import top.fosin.anan.platform.util.PermissionUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统机构权限系统机构权限表服务实现类
 *
 * @author fosin
 * @date 2018-11-18 17:26:40
 */
@Service
@Lazy
public class OrganizationPermissionServiceImpl implements OrganizationPermissionService {
    private final OrganizationPermissionRepository orgPermissionRepository;

    public OrganizationPermissionServiceImpl(OrganizationPermissionRepository orgPermissionRepository) {
        this.orgPermissionRepository = orgPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public OrganizationPermissionRepository getRepository() {
        return orgPermissionRepository;
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
    @Transactional(rollbackFor = Exception.class)
    public List<AnanOrganizationPermissionRespDto> updateInBatch(String deleteCol, Long organizId, Collection<AnanOrganizationPermissionReqDto> dtos) {
        Assert.notNull(organizId, "传入的机构ID不能为空!");

        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getOrganizId().equals(organizId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        Collection<AnanOrganizationPermissionEntity> afterPermissions = BeanUtil.copyCollectionProperties(dtos, AnanOrganizationPermissionEntity.class);

        List<AnanOrganizationPermissionEntity> beforePermissions = orgPermissionRepository.findByOrganizId(organizId);

        PermissionUtil.deletePermission(beforePermissions, afterPermissions, orgPermissionRepository);

        PermissionUtil.saveNewPermission(beforePermissions, afterPermissions.stream().map(versionPermissionEntity -> {
            AnanOrganizationPermissionEntity entity = new AnanOrganizationPermissionEntity();
            entity.setOrganizId(organizId);
            entity.setPermissionId(versionPermissionEntity.getPermissionId());
            return entity;
        }).collect(Collectors.toList()), orgPermissionRepository);

        orgPermissionRepository.deleteByOrganizId(organizId);

        return BeanUtil.copyCollectionProperties(orgPermissionRepository.saveAll(afterPermissions), AnanOrganizationPermissionRespDto.class);
    }
}
