package top.fosin.anan.platform.modules.organization.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrganizationPermissionRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationPermission;
import top.fosin.anan.platform.modules.organization.dao.OrganizationPermissionDao;
import top.fosin.anan.platform.modules.organization.service.inter.OrganizationPermissionService;
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
    private final OrganizationPermissionDao orgPermissionRepository;

    public OrganizationPermissionServiceImpl(OrganizationPermissionDao orgPermissionRepository) {
        this.orgPermissionRepository = orgPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public OrganizationPermissionDao getRepository() {
        return orgPermissionRepository;
    }

    @Override
    public List<OrganizationPermissionRespDto> findByOrganizId(Long organizId) {
        return BeanUtil.copyProperties(getRepository().findByOrganizId(organizId), OrganizationPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getRepository().countByPermissionId(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrganizationPermissionRespDto> updateInBatch(String deleteCol, Long organizId, Collection<OrganizationPermissionReqDto> dtos) {
        Assert.notNull(organizId, "传入的机构ID不能为空!");

        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getOrganizId().equals(organizId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        Collection<OrganizationPermission> afterPermissions = BeanUtil.copyProperties(dtos, OrganizationPermission.class);

        List<OrganizationPermission> beforePermissions = orgPermissionRepository.findByOrganizId(organizId);

        PermissionUtil.deletePermission(beforePermissions, afterPermissions, orgPermissionRepository);

        PermissionUtil.saveNewPermission(beforePermissions, afterPermissions.stream().map(versionPermissionEntity -> {
            OrganizationPermission entity = new OrganizationPermission();
            entity.setOrganizId(organizId);
            entity.setPermissionId(versionPermissionEntity.getPermissionId());
            return entity;
        }).collect(Collectors.toList()), orgPermissionRepository);

        orgPermissionRepository.deleteByOrganizId(organizId);

        return BeanUtil.copyProperties(orgPermissionRepository.saveAll(afterPermissions), OrganizationPermissionRespDto.class);
    }
}
