package com.github.fosin.anan.auth.security;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.auth.service.inter.RolePermissionService;
import com.github.fosin.anan.auth.service.inter.UserPermissionService;
import com.github.fosin.anan.auth.service.inter.UserService;
import com.github.fosin.anan.platformapi.constant.SystemConstant;
import com.github.fosin.anan.platformapi.dto.AnanUserDetail;
import com.github.fosin.anan.platformapi.entity.*;
import com.github.fosin.anan.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

import java.util.*;

/**
 * 2017/12/27.
 * Time:16:37
 *
 * @author fosin
 */
@Service
@Lazy
@Slf4j
public class AnanUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里的username对应anan_user.usercode
        AnanUserEntity userEntity = userService.findByUsercode(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("用户:" + username + "不存在!");
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        List<AnanUserRoleEntity> userRoles = userEntity.getUserRoles();
        Long userId = userEntity.getId();
        Set<AnanPermissionEntity> userPermissions = new TreeSet<>((o1, o2) -> {
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
        for (AnanUserRoleEntity userRole : userRoles) {
            AnanRoleEntity role = userRole.getRole();
            if (role.getStatus() != 0) {
                continue;
            }
            Long roleId = role.getId();

            //获取角色权限
            List<AnanRolePermissionEntity> rolePermissionList = rolePermissionService.findByRoleId(roleId);
            for (AnanRolePermissionEntity rolePermissionEntity : rolePermissionList) {
                Long permissionId = rolePermissionEntity.getPermissionId();
                AnanPermissionEntity entity = permissionService.findById(permissionId);
                // 只添加状态为启用的权限
                if (entity.getStatus() == 0) {
                    userPermissions.add(entity);
                    grantedAuthoritySet.add(new SimpleGrantedAuthority(permissionId + ""));
                }
            }
        }
        //获取用户权限
        List<AnanUserPermissionEntity> userPermissionList = userPermissionService.findByUserId(userId);
        for (AnanUserPermissionEntity userPermissionEntity : userPermissionList) {
            int addmode = userPermissionEntity.getAddMode();
            Long permissionId = userPermissionEntity.getPermissionId();
            AnanPermissionEntity entity = permissionService.findById(permissionId);
            // 只操作状态为启用的权限
            if (entity.getStatus() == 0) {
                //获取用户增权限
                if (addmode == 0) {
                    userPermissions.add(entity);
                    grantedAuthoritySet.add(new SimpleGrantedAuthority(permissionId + ""));
                } else { //移除用户减权限
                    userPermissions.remove(entity);
                    grantedAuthoritySet.remove(new SimpleGrantedAuthority(permissionId + ""));
                }
            }
        }

        AnanPermissionEntity permissionTree = TreeUtil.createTree(userPermissions, SystemConstant.ROOT_PERMISSION_ID, "id", "pid", "children");

        AnanUserDetail user = new AnanUserDetail(userEntity, permissionTree, grantedAuthoritySet);
        log.debug("UserDetailsServiceImpl User:" + user.toString());
        return user;
    }
}
