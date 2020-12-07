package com.github.fosin.anan.auth.service;

import com.github.fosin.anan.auth.service.inter.PermissionService;
import com.github.fosin.anan.cloudresource.constant.RedisConstant;
import com.github.fosin.anan.cloudresource.constant.SystemConstant;
import com.github.fosin.anan.cloudresource.dto.AnanUserAllPermissionDto;
import com.github.fosin.anan.cloudresource.dto.request.AnanPermissionRetrieveDto;
import com.github.fosin.anan.jpa.repository.IJpaRepository;
import com.github.fosin.anan.platformapi.entity.AnanPermissionEntity;
import com.github.fosin.anan.platformapi.entity.AnanServiceEntity;
import com.github.fosin.anan.platformapi.entity.AnanUserAllPermissionsEntity;
import com.github.fosin.anan.platformapi.repository.AnanServiceRepository;
import com.github.fosin.anan.platformapi.repository.PermissionRepository;
import com.github.fosin.anan.platformapi.repository.UserAllPermissionsRepository;
import com.github.fosin.anan.util.TreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserAllPermissionsRepository userAllPermissionsRepository;
    private final AnanServiceRepository serviceRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, UserAllPermissionsRepository userAllPermissionsRepository, AnanServiceRepository serviceRepository) {
        this.permissionRepository = permissionRepository;
        this.userAllPermissionsRepository = userAllPermissionsRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_PERMISSION, key = "#id")
    public AnanPermissionEntity findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnanPermissionRetrieveDto> findByServiceCode(String serviceCode) {
        AnanServiceEntity serviceEntity = serviceRepository.findOneByCode(serviceCode);
        List<AnanPermissionEntity> byServiceCode = permissionRepository.findAllByServiceId(serviceEntity.getId());
        List<AnanPermissionRetrieveDto> retrieveDtos = new ArrayList<>();
        for (AnanPermissionEntity ananPermissionEntity : byServiceCode) {
            AnanPermissionRetrieveDto retrieveDto = new AnanPermissionRetrieveDto();
            BeanUtils.copyProperties(ananPermissionEntity, retrieveDto);
            retrieveDto.setId(ananPermissionEntity.getId());
            retrieveDtos.add(retrieveDto);
        }
        return retrieveDtos;
    }

    @Override
    @Cacheable(value = RedisConstant.ANAN_USER_ALL_PERMISSIONS, key = "#userId")
    public List<AnanUserAllPermissionsEntity> findByUserId(Long userId) {
        return userAllPermissionsRepository.findByUserId(userId);
    }

    @Override
    public AnanUserAllPermissionDto findTreeByUserId(Long userId) {
        Set<AnanUserAllPermissionDto> userPermissions = new TreeSet<>((o1, o2) -> {
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
                Long id = entity.getId();
                //获取用户增权限
                if (entity.getAddMode() == 0) {
                    userPermissions.add(entity.convert2Dto());
                } else { //
                    userPermissions.remove(entity.convert2Dto());
                }
            }
        }

        return TreeUtil.createTree(userPermissions, SystemConstant.ROOT_PERMISSION_ID, "id", "pid", "children");
    }

    @Override
    public IJpaRepository<AnanPermissionEntity, Long> getRepository() {
        return permissionRepository;
    }

}
