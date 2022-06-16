package top.fosin.anan.platform.modules.organization.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.platform.modules.organization.dao.OrgPermissionDao;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionReqDto;
import top.fosin.anan.platform.modules.organization.dto.OrgPermissionRespDto;
import top.fosin.anan.platform.modules.organization.entity.OrganizationPermission;
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
@AllArgsConstructor
public class OrgPermissionServiceImpl implements OrgPermissionService {
    private final OrgPermissionDao orgPermissionDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<OrgPermissionRespDto> updateInBatch(Long organizId, Collection<OrgPermissionReqDto> dtos) {
        Assert.notNull(organizId, "传入的机构ID不能为空!");

        Assert.isTrue(dtos.stream().allMatch(entity -> entity.getOrganizId().equals(organizId)), "需要更新的数据集中有与版本ID不匹配的数据!");

        Collection<OrganizationPermission> afterPermissions = BeanUtil.copyProperties(dtos, OrganizationPermission.class);

        List<OrganizationPermission> beforePermissions = orgPermissionDao.findByOrganizId(organizId);

        PermissionUtil.deletePermission(beforePermissions, afterPermissions, orgPermissionDao);

        PermissionUtil.saveNewPermission(beforePermissions, afterPermissions.stream().map(versionPermissionEntity -> {
            OrganizationPermission entity = new OrganizationPermission();
            entity.setOrganizId(organizId);
            entity.setPermissionId(versionPermissionEntity.getPermissionId());
            return entity;
        }).collect(Collectors.toList()), orgPermissionDao);

        orgPermissionDao.deleteByOrganizId(organizId);

        return BeanUtil.copyProperties(orgPermissionDao.saveAll(afterPermissions), OrgPermissionRespDto.class);
    }

    /**
     * 获取DAO
     */
    @Override
    public OrgPermissionDao getDao() {
        return orgPermissionDao;
    }
}
