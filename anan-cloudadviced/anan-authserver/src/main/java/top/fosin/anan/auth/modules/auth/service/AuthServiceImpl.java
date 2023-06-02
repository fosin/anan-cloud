package top.fosin.anan.auth.modules.auth.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.fosin.anan.auth.modules.auth.dao.UserAllPermissionsDao;
import top.fosin.anan.auth.modules.auth.po.UserAllPermissions;
import top.fosin.anan.auth.modules.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.PlatformRedisConstant;
import top.fosin.anan.cloudresource.entity.UserAllPermissionTreeVO;
import top.fosin.anan.cloudresource.entity.res.UserAllPermissionsRespDTO;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.TreeUtil;
import top.fosin.anan.data.prop.PidProp;
import top.fosin.anan.data.prop.TreeProp;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author fosin
 * @date 2017/12/29
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserAllPermissionsDao userAllPermissionsDao;

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId",
            unless = "#result == null || #result.size() == 0")
    public List<UserAllPermissionsRespDTO> listPermissionsByUserId(Long userId) {
        return BeanUtil.copyProperties(userAllPermissionsDao.findByUserId(userId), UserAllPermissionsRespDTO.class);
    }

    @Override
    @Cacheable(value = PlatformRedisConstant.ANAN_USER_PERMISSION_TREE, key = "#userId",
            unless = "#result == null")
    public UserAllPermissionTreeVO treeByUserId(Long userId) {
        Set<UserAllPermissionTreeVO> userPermissions = new TreeSet<>((o1, o2) -> {
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
                UserAllPermissionTreeVO treeDto = BeanUtil.copyProperties(entity, UserAllPermissionTreeVO.class);
                if (entity.getAddMode() == 0) {
                    userPermissions.add(treeDto);
                } else { //
                    userPermissions.remove(treeDto);
                }
            }
        }
        //找到permission权限树唯一的一个根节点
        UserAllPermissionTreeVO treeDto = userPermissions.stream()
                .filter(PidProp::isRoot)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("未分配根节点权限,请联系管理员核对权限!"));
        return TreeUtil.createTree(userPermissions, treeDto.getId(), TreeProp.ID_NAME,
                TreeProp.PID_NAME, TreeProp.CHILDREN_NAME, TreeProp.LEAF_NAME);
    }
}
