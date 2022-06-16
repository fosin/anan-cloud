package top.fosin.anan.auth.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fosin.anan.auth.dao.UserAllPermissionsDao;
import top.fosin.anan.auth.dao.UserDao;
import top.fosin.anan.auth.entity.User;
import top.fosin.anan.auth.entity.UserAllPermissions;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.dto.UserAllPermissionTreeDto;
import top.fosin.anan.cloudresource.dto.UserAuthDto;
import top.fosin.anan.cloudresource.dto.res.OrgRespDto;
import top.fosin.anan.cloudresource.dto.res.UserAllPermissionsRespDto;
import top.fosin.anan.cloudresource.service.inter.OrgFeignService;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.TreeUtil;
import top.fosin.anan.model.prop.PidProp;
import top.fosin.anan.model.prop.TreeProp;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserAllPermissionsDao userAllPermissionsDao;
    private final UserDao userDao;
    private final OrgFeignService orgFeignService;

    public AuthServiceImpl(UserAllPermissionsDao userAllPermissionsDao,
                           UserDao userDao,
                           OrgFeignService orgFeignService) {
        this.userAllPermissionsDao = userAllPermissionsDao;
        this.userDao = userDao;
        this.orgFeignService = orgFeignService;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER, key = "#usercode", unless = "#result==null")
    @Transactional(readOnly = true)
    public UserAuthDto findByUsercode(String usercode) {
        User userEntity = userDao.findByUsercode(usercode);
        if (userEntity != null) {
            UserAuthDto dto = userEntity.toAuthDto();
            Long organizId = dto.getOrganizId();
            if (organizId > 0) {
                OrgRespDto org = orgFeignService.findOneById(organizId)
                        .orElseThrow("未找到对应机构信息" + organizId + ",请联系管理员核对!");
                dto.setTopId(org.getTopId());
            }
            return dto;
        }
        return null;
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId",
            unless = "#result == null || #result.size() == 0")
    public List<UserAllPermissionsRespDto> findByUserId(Long userId) {
        return BeanUtil.copyProperties(userAllPermissionsDao.findByUserId(userId), UserAllPermissionsRespDto.class);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#userId",
            unless = "#result == null")
    public UserAllPermissionTreeDto treeByUserId(Long userId) {
        Set<UserAllPermissionTreeDto> userPermissions = new TreeSet<>((o1, o2) -> {
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
        List<UserAllPermissions> permissionEntities = userAllPermissionsDao.findByUserId(userId);
        for (UserAllPermissions entity : permissionEntities) {
            // 只操作状态为启用的权限
            if (entity.getStatus() == 0) {
                //获取用户增权限
                UserAllPermissionTreeDto treeDto = BeanUtil.copyProperties(entity, UserAllPermissionTreeDto.class);
                if (entity.getAddMode() == 0) {
                    userPermissions.add(treeDto);
                } else { //
                    userPermissions.remove(treeDto);
                }
            }
        }
        //找到permission权限树唯一的一个根节点
        UserAllPermissionTreeDto treeDto = userPermissions.stream()
                .filter(PidProp::isRoot)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未分配根节点权限,请联系管理员核对权限!"));
        return TreeUtil.createTree(userPermissions, treeDto.getId(), TreeProp.ID_NAME,
                TreeProp.PID_NAME, TreeProp.CHILDREN_NAME, TreeProp.LEAF_NAME);
    }
}
