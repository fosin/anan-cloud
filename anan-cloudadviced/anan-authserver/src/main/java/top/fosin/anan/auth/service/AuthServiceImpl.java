package top.fosin.anan.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import top.fosin.anan.auth.entity.AnanUserAllPermissionsEntity;
import top.fosin.anan.auth.entity.AnanUserEntity;
import top.fosin.anan.auth.entity.AnanUserRoleEntity;
import top.fosin.anan.auth.repository.UserAllPermissionsRepository;
import top.fosin.anan.auth.repository.UserRepository;
import top.fosin.anan.auth.service.inter.AuthService;
import top.fosin.anan.cloudresource.constant.RedisConstant;
import top.fosin.anan.cloudresource.constant.SystemConstant;
import top.fosin.anan.cloudresource.dto.AnanUserAllPermissionTreeDto;
import top.fosin.anan.cloudresource.dto.res.AnanUserAllPermissionsRespDto;
import top.fosin.anan.core.util.BeanUtil;
import top.fosin.anan.core.util.TreeUtil;
import top.fosin.anan.model.dto.TreeDto;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 2017/12/29.
 * Time:12:38
 *
 * @author fosin
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserAllPermissionsRepository userAllPermissionsRepository;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserAllPermissionsRepository userAllPermissionsRepository,
                           UserRepository userRepository) {
        this.userAllPermissionsRepository = userAllPermissionsRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER, key = "#usercode")
    @Transactional(readOnly = true)
    public AnanUserEntity findByUsercode(String usercode) {
        Assert.notNull(usercode, "用户工号不能为空!");
        //该代码看似无用，其实是为了解决懒加载和缓存先后问题
        AnanUserEntity userEntity = userRepository.findByUsercode(usercode);
        if (userEntity != null) {
            List<AnanUserRoleEntity> userRoles = userEntity.getUserRoles();
            log.debug(userRoles.toString());
        }
        return userEntity;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId")
    public List<AnanUserAllPermissionsRespDto> findByUserId(Long userId) {
        return BeanUtil.copyCollectionProperties(userAllPermissionsRepository.findByUserId(userId), AnanUserAllPermissionsRespDto.class);
    }

    @Override
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
        List<AnanUserAllPermissionsEntity> permissionEntities = userAllPermissionsRepository.findByUserId(userId);

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
