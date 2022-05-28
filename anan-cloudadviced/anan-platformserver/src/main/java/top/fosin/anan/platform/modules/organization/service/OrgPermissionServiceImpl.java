package top.fosin.anan.platform.modules.organization.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationPermission;
import top.fosin.anan.platform.modules.organization.dao.OrgPermissionDao;
import top.fosin.anan.platform.modules.organization.service.inter.OrgPermissionService;
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
public class OrgPermissionServiceImpl implements OrgPermissionService {
    private final OrgPermissionDao orgPermissionRepository;

    public OrgPermissionServiceImpl(OrgPermissionDao orgPermissionRepository) {
        this.orgPermissionRepository = orgPermissionRepository;
    }

    /**
     * 获取DAO
     */
    @Override
    public OrgPermissionDao getDao() {
        return orgPermissionRepository;
    }

    @Override
    public List<OrgPermissionRespDto> findByOrganizId(Long organizId) {
        return BeanUtil.copyProperties(getDao().findByOrganizId(organizId), OrgPermissionRespDto.class);
    }

    @Override
    public long countByPermissionId(Long permissionId) {
        return getDao().countByPermissionId(permissionId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrgPermissionRespDto> updateInBatch(String deleteCol, Long organizId, Collection<OrgPermissionReqDto> dtos) {
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

        return BeanUtil.copyProperties(orgPermissionRepository.saveAll(afterPermissions), OrgPermissionRespDto.class);
    }
}
