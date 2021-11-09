package top.fosin.anan.auth.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.auth.entity.AnanOrganizationEntity;
import top.fosin.anan.auth.entity.AnanUserAllPermissionsEntity;
import top.fosin.anan.auth.entity.AnanUserEntity;
import top.fosin.anan.auth.repository.OrganizationRepository;
import top.fosin.anan.auth.repository.UserAllPermissionsRepository;
import top.fosin.anan.auth.repository.UserRepository;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.AnanUserAllPermissionTreeDto;
import top.fosin.anan.cloudresource.dto.AnanUserAuthDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserAllPermissionsRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.TreeUtil;
import top.fosin.anan.model.dto.TreeDto;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserAllPermissionsRepository userAllPermissionsRepo;
    private final UserRepository userRepo;
    private final OrganizationRepository orgRepo;

    public AuthServiceImpl(UserAllPermissionsRepository userAllPermissionsRepo,
                           UserRepository userRepo, OrganizationRepository orgRepo) {
        this.userAllPermissionsRepo = userAllPermissionsRepo;
        this.userRepo = userRepo;
        this.orgRepo = orgRepo;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER, key = "#usercode", unless = "#result==null")
    @Transactional(readOnly = true)
    public AnanUserAuthDto findByUsercode(String usercode) {
        AnanUserEntity userEntity = userRepo.findByUsercode(usercode);
        if (userEntity != null) {
            AnanUserAuthDto dto = userEntity.conert2Dto();
            Long organizId = dto.getOrganizId();
            if (organizId > 0) {
                AnanOrganizationEntity organization = orgRepo.getOne(organizId);
                dto.setTopId(organization.getTopId());
            }
            return dto;
        }
        return null;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId", unless = "#result == null || #result.size() == 0")
    public List<AnanUserAllPermissionsRespDto> findByUserId(Long userId) {
        return BeanUtil.copyCollectionProperties(userAllPermissionsRepo.findByUserId(userId), AnanUserAllPermissionsRespDto.class);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#userId", unless = "#result == null")
    public AnanUserAllPermissionTreeDto findTreeByUserId(Long userId) {
        Set<AnanUserAllPermissionTreeDto> userPermissions = new TreeSet<>((o1, o2) -> {
            long subId = o1.getId() - o2.getId();
            if (subId == 0) {
                return 0;
            }
            int subLevel = o1.getLevel() - o2.getLevel();
            if (subLevel != 0) {
                return subLevel;
            }
            int subSort = o1.getSort() - o2.getSort();
            if (subSort != 0) {
                return subSort;
            }

            return o1.getCode().compareToIgnoreCase(o2.getCode());
        });
        List<AnanUserAllPermissionsEntity> permissionEntities = userAllPermissionsRepo.findByUserId(userId);

        for (AnanUserAllPermissionsEntity entity : permissionEntities) {
            // 只操作状态为启用的权限
            if (entity.getStatus() == 0) {
                //获取用户增权限
                if (entity.getAddMode() == 0) {
                    userPermissions.add(entity.convert2Dto());
                } else { //
                    userPermissions.remove(entity.convert2Dto());
                }
            }
        }
        Assert.isTrue(userPermissions.stream().anyMatch(ananUserAllPermissionDto -> SystemConstant.ROOT_PERMISSION_ID.equals(ananUserAllPermissionDto.getId())), "未分配根节点权限,请联系管理员核对权限!");
        return TreeUtil.createTree(userPermissions, SystemConstant.ROOT_PERMISSION_ID, TreeDto.ID_NAME, TreeDto.PID_NAME, TreeDto.CHILDREN_NAME);
    }
}
